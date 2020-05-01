package com.joshua.StockManagementSystem.joseph_impl.domain;

import com.joshua.StockManagementSystem.joseph_api.api.payload.UpsertTransactionDetailRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.api.payload.UpsertTransactionHeaderRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.domain.TransactionService;
import com.joshua.StockManagementSystem.joseph_api.infrastructure.dao.CustomerDAO;
import com.joshua.StockManagementSystem.joseph_api.infrastructure.dao.ItemDAO;
import com.joshua.StockManagementSystem.joseph_api.infrastructure.dao.TransactionDAO;
import com.joshua.StockManagementSystem.joseph_api.model.TransactionHeader;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.PostgresHelper;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.adapter.TransactionAdapter;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.dao.spec.TransactionSpec;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout.CustomerDataEntity;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout.ItemDataEntity;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout.TransactionDetailDataEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static com.joshua.StockManagementSystem.joseph_impl.infrastructure.adapter.TransactionAdapter.convertUpsertPayloadToDataEntity;

@Component("transactionV1Service")
@Service
public class TransactionServiceImpl implements TransactionService {

  private final TransactionDAO transactionDAO;
  private final ItemDAO itemDAO;
  private final CustomerDAO customerDAO;
  private final Logger log = LoggerFactory.getLogger(TransactionServiceImpl.class);

  @Autowired
  public TransactionServiceImpl(@Qualifier("postgresTransaction") TransactionDAO transactionDAO,
                                @Qualifier("postgresItem") ItemDAO itemDAO,
                                @Qualifier("postgresCust") CustomerDAO customerDAO) {
    this.transactionDAO = transactionDAO;
    this.itemDAO = itemDAO;
    this.customerDAO = customerDAO;
  }

  @Override
  public List<String> insert(UpsertTransactionHeaderRequestPayload upsertTransactionHeaderRequestPayload) {
    List<String> stats = new LinkedList<>();
    List<ItemDataEntity> itemDataEntities = new LinkedList<>();

    TransactionSpec transactionSpec = convertUpsertPayloadToDataEntity(upsertTransactionHeaderRequestPayload);

    log.info("validating customer");
    Optional<CustomerDataEntity> customer = customerDAO.show(upsertTransactionHeaderRequestPayload.getCustomerId());
    if(customer == null) {
      stats.add("Customer "+upsertTransactionHeaderRequestPayload.getCustomerId()+ PostgresHelper.NOTFOUND);
    }

    // validate and get price each item
    log.info("validating each item");
    int sz = upsertTransactionHeaderRequestPayload.getTransactionDetails().size();
    for(int i = 0;i<sz;i++){
      UpsertTransactionDetailRequestPayload detail = upsertTransactionHeaderRequestPayload.getTransactionDetails().get(i);
      log.info("validating item "+detail.getItemCode());
      Optional<ItemDataEntity> currItem = itemDAO.show(detail.getItemCode());
      if(currItem == null){
        stats.add(PostgresHelper.ITEM+detail.getItemCode()+ PostgresHelper.NOTFOUND);continue;
      }
      if(currItem.get().getStock() < detail.getQuantity()){
        stats.add(PostgresHelper.ITEM+currItem.get().getName()+" is much more than stock");continue;
      }

      // update stock
      currItem.get().setStock(currItem.get().getStock() - detail.getQuantity());
      itemDataEntities.add(currItem.get());
      transactionSpec.getTransactionDetailDataEntityList().get(i).setPrice(currItem.get().getPrice());

    }

    if(!stats.isEmpty()){
      return stats;
    }

    if(transactionDAO.insert(transactionSpec.getTransactionHeaderDataEntity(), transactionSpec.getTransactionDetailDataEntityList()) == 1){

      stats.add(PostgresHelper.TRANHEAD+upsertTransactionHeaderRequestPayload.getId()+ PostgresHelper.SUCCESS + PostgresHelper.INSERTED);

      int i = 0;
      for (TransactionDetailDataEntity detail : transactionSpec.getTransactionDetailDataEntityList()){
        stats.add(PostgresHelper.TRANHEAD+"of item "+detail.getItemCode() +PostgresHelper.SUCCESS + PostgresHelper.INSERTED);
        // applying update stock item
        log.info("applying update stock item "+itemDataEntities.get(i).getItemCode());
        itemDAO.update(itemDataEntities.get(i++));
      }


    }else{
      stats.add(PostgresHelper.TRANHEAD+upsertTransactionHeaderRequestPayload.getId()+ PostgresHelper.FAIL + PostgresHelper.INSERTED);
    }
    return stats;
  }

  @Override
  public List<TransactionHeader> index() {
    List<TransactionSpec> transactionSpecs = transactionDAO.index();
    return TransactionAdapter.convertDataEntitiesToModels(transactionSpecs);
  }

  @Override
  public TransactionHeader show(String id) {
    return null;
  }

  @Override
  public List<String> update(UpsertTransactionHeaderRequestPayload upsertTransactionHeaderRequestPayload) {
    return null;
  }

  @Override
  public List<String> delete(String id) {
    return null;
  }
}

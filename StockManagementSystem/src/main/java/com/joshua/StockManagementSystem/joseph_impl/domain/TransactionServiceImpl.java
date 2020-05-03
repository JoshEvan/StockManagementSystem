package com.joshua.StockManagementSystem.joseph_impl.domain;

import com.joshua.StockManagementSystem.joseph_api.api.payload.index.IndexTransactionRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.api.payload.upsert.UpsertTransactionDetailRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.api.payload.upsert.UpsertTransactionHeaderRequestPayload;
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

import java.util.*;

import static com.joshua.StockManagementSystem.joseph_impl.infrastructure.adapter.TransactionAdapter.*;

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
  public List<TransactionHeader> index(IndexTransactionRequestPayload indexTransactionRequestPayload) {
    List<TransactionSpec> transactionSpecs = transactionDAO.index(indexTransactionRequestPayload);
    return TransactionAdapter.convertTransactionSpecsToModels(transactionSpecs);
  }

  @Override
  public TransactionHeader show(String id) {
    TransactionSpec transactionSpec = transactionDAO.show(id).orElse(null);
    if(transactionSpec == null) return new TransactionHeader();
    return convertTransactionSpecToModel(transactionSpec);
  }

  @Override
  public List<String> update(UpsertTransactionHeaderRequestPayload upsertTransactionHeaderRequestPayload) {
    List<String> stats = new LinkedList<>();
    List<ItemDataEntity> itemDataEntities = new LinkedList<>();
    TransactionSpec previousTransactionSpec = transactionDAO.show(upsertTransactionHeaderRequestPayload.getId()).orElse(null);
    if(previousTransactionSpec == null){
      return Collections.singletonList("Transaction "+upsertTransactionHeaderRequestPayload.getId()+ PostgresHelper.NOTFOUND);
    }

    TransactionSpec updatedTransactionSpec = convertUpsertPayloadToDataEntity(upsertTransactionHeaderRequestPayload);

    log.info("validating customer");
    Optional<CustomerDataEntity> customer = customerDAO.show(upsertTransactionHeaderRequestPayload.getCustomerId());
    if(customer == null) {
      stats.add("Customer "+upsertTransactionHeaderRequestPayload.getCustomerId()+ PostgresHelper.NOTFOUND);
    }

    // validate and get price each item
    log.info("validating each item");
    int sz = upsertTransactionHeaderRequestPayload.getTransactionDetails().size();
    for(int i = 0;i<sz;i++){
      UpsertTransactionDetailRequestPayload updatedDetail = upsertTransactionHeaderRequestPayload.getTransactionDetails().get(i);
      TransactionDetailDataEntity previousDetail = previousTransactionSpec.getTransactionDetailDataEntityList().get(i);

      log.info("validating item "+updatedDetail.getItemCode());
      Optional<ItemDataEntity> currItem = itemDAO.show(updatedDetail.getItemCode());
      if(currItem == null){
        stats.add(PostgresHelper.ITEM+updatedDetail.getItemCode()+ PostgresHelper.NOTFOUND);continue;
      }
      if(currItem.get().getStock() < updatedDetail.getQuantity()){
        stats.add(PostgresHelper.ITEM+currItem.get().getName()+" is much more than stock");continue;
      }

      // update stock
      currItem.get().setStock(currItem.get().getStock() + previousDetail.getQuantity() - updatedDetail.getQuantity());
      itemDataEntities.add(currItem.get());
      updatedTransactionSpec.getTransactionDetailDataEntityList().get(i).setPrice(previousDetail.getPrice());

      // PRICE OF EACH ITEM WILL BE SET AS SAME AS ITEM'S PRICE BEFORE
      // IF ITEM'S PRICE CHANGED, THEN USER SHOULD DELETE AND INSERT NEW TRANSACTIONS

    }

    if(!stats.isEmpty()){
      return stats;
    }

    if(transactionDAO.update(updatedTransactionSpec.getTransactionHeaderDataEntity(), updatedTransactionSpec.getTransactionDetailDataEntityList()) == 1){

      stats.add(PostgresHelper.TRANHEAD+upsertTransactionHeaderRequestPayload.getId()+ PostgresHelper.SUCCESS + PostgresHelper.UPDATED);

      int i = 0;
      for (TransactionDetailDataEntity detail : updatedTransactionSpec.getTransactionDetailDataEntityList()){
        stats.add(PostgresHelper.TRANHEAD+"of item "+detail.getItemCode() +PostgresHelper.SUCCESS + PostgresHelper.UPDATED);
        // applying update stock item
        log.info("applying update stock item "+itemDataEntities.get(i).getItemCode());
        itemDAO.update(itemDataEntities.get(i++));
      }


    }else{
      stats.add(PostgresHelper.TRANHEAD+upsertTransactionHeaderRequestPayload.getId()+ PostgresHelper.FAIL + PostgresHelper.UPDATED);
    }
    return stats;


  }

  @Override
  public List<String> delete(String id) {
    List<String> stats = new LinkedList<>();
    // return item's stock as previous
    TransactionSpec transactionSpec = transactionDAO.show(id).orElse(null);

    log.info("deleting "+PostgresHelper.TRANHEAD+id);
    Integer flag = transactionDAO.delete(id);
    log.info("success delete "+PostgresHelper.TRANHEAD+id);

    if(flag == 0){
     return Collections.singletonList(PostgresHelper.TRANHEAD+id+PostgresHelper.FAIL+ PostgresHelper.REMOVED);
    }
    log.info(PostgresHelper.TRANHEAD+id+PostgresHelper.REMOVED+ PostgresHelper.SUCCESS);
    stats.add(PostgresHelper.TRANHEAD+id+PostgresHelper.SUCCESS+ PostgresHelper.REMOVED);

    if(transactionSpec == null) return Collections.singletonList(PostgresHelper.TRANHEAD + " " + id + PostgresHelper.NOTFOUND);

    for(TransactionDetailDataEntity detailDataEntity : transactionSpec.getTransactionDetailDataEntityList()){
      ItemDataEntity item = itemDAO.show(detailDataEntity.getItemCode()).orElse(null);

      if(item == null) return Collections.singletonList(PostgresHelper.ITEM + id + PostgresHelper.NOTFOUND);

      log.info("rolling back "+PostgresHelper.ITEM+item.getItemCode()+" stock");
      item.setStock(item.getStock()+detailDataEntity.getQuantity());
      itemDAO.update(item);
      stats.add(PostgresHelper.ITEM+detailDataEntity.getItemCode()+PostgresHelper.SUCCESS+"rolled back");
    }
    return stats;
  }
}

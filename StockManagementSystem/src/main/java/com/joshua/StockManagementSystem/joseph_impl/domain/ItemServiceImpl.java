package com.joshua.StockManagementSystem.joseph_impl.domain;

import com.joshua.StockManagementSystem.joseph_api.api.payload.index.IndexTransactionRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.api.payload.upsert.UpsertItemRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.domain.ItemService;
import com.joshua.StockManagementSystem.joseph_api.infrastructure.dao.ItemDAO;
import com.joshua.StockManagementSystem.joseph_api.infrastructure.dao.TransactionDAO;
import com.joshua.StockManagementSystem.joseph_api.model.Item;
import com.joshua.StockManagementSystem.joseph_api.model.TransactionDetail;
import com.joshua.StockManagementSystem.joseph_api.model.TransactionHeader;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.PostgresHelper;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.adapter.TransactionAdapter;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.dao.spec.TransactionSpec;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout.ItemDataEntity;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout.TransactionHeaderDataEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static com.joshua.StockManagementSystem.joseph_impl.infrastructure.PostgresHelper.*;
import static com.joshua.StockManagementSystem.joseph_impl.infrastructure.adapter.ItemAdapter.convertDataEntitiesToModels;
import static com.joshua.StockManagementSystem.joseph_impl.infrastructure.adapter.ItemAdapter.convertUpsertPayloadToDataEntity;

@Component("itemV1Service")
@Service
public class ItemServiceImpl implements ItemService {
  private final ItemDAO itemDAO;
  private final TransactionDAO transactionDAO;

  @Autowired
  public ItemServiceImpl(@Qualifier("postgresItem") ItemDAO itemDAO,
                         @Qualifier("postgresTransaction") TransactionDAO transactionDAO) {
    this.itemDAO = itemDAO;
    this.transactionDAO = transactionDAO;
  }

  @Override
  public List<String> insert(UpsertItemRequestPayload upsertItemRequestPayload) {
    List<String> stats = new LinkedList<>();
    if(itemDAO.insert(convertUpsertPayloadToDataEntity(upsertItemRequestPayload)) == 1){
      stats.add(ITEM+ upsertItemRequestPayload.getName()+ SUCCESS+" "+ PostgresHelper.INSERTED);
    }else{
      stats.add(ITEM+ upsertItemRequestPayload.getName()+ FAIL+" "+ PostgresHelper.INSERTED);
    }
    return stats;
  }

  @Override
  public List<Item> index() {
    List<ItemDataEntity> itemDataEntities = itemDAO.index();
    List<TransactionDetail> transactionDetails =
      TransactionAdapter.convertDetailDataEntitiesToModels(
        new TransactionSpec().setTransactionHeaderDataEntity(new TransactionHeaderDataEntity().setId("not used")).setTransactionDetailDataEntityList(
          transactionDAO.indexDetails(new IndexTransactionRequestPayload(), new HashMap<>())));

    HashMap<String, BigDecimal> amountIncome = new HashMap<>();
    HashMap<String, Integer> amountSold = new HashMap<>();

    for(TransactionDetail detail: transactionDetails){
      if(!amountIncome.containsKey(detail.getItemCode())){
        amountIncome.put(detail.getItemCode(),detail.getSubTotalDec());
      }else{
        amountIncome.replace(detail.getItemCode(),detail.getSubTotalDec().add(amountIncome.get(detail.getItemCode())));
      }

      if(!amountSold.containsKey(detail.getItemCode())){
        amountSold.put(detail.getItemCode(),detail.getQuantity());
      }else{
        amountSold.replace(detail.getItemCode(),detail.getQuantity()+amountSold.get(detail.getItemCode()));
      }
    }

    List<Item> items = convertDataEntitiesToModels(itemDataEntities);
    for(Item item: items){
      if(amountIncome.containsKey(item.getItemCode()))
        item.setIncomeAmountDec(amountIncome.get(item.getItemCode()))
        .setIncomeAmount(formatCurrency(amountIncome.get(item.getItemCode())));
      else item.setIncomeAmount(formatCurrency(BigDecimal.valueOf(0))).setIncomeAmountDec(BigDecimal.valueOf(0));

      if(amountSold.containsKey(item.getItemCode()))
        item.setTotalSold(amountSold.get(item.getItemCode()));
      else item.setTotalSold(0);
    }

    return items;
  }

  @Override
  public Item show(String id) {
    ItemDataEntity dataEntity = itemDAO.show(id).orElse(null);
    if(dataEntity == null) return null;
    List<TransactionDetail> details = TransactionAdapter.convertDetailDataEntitiesToModels(
            new TransactionSpec()
            .setTransactionDetailDataEntityList(transactionDAO.indexDetails(
                    new IndexTransactionRequestPayload().setItemFilter(Collections.singletonList(id)),
                    new HashMap<>()))
            .setTransactionHeaderDataEntity(new TransactionHeaderDataEntity().setId("not used"))
    );
    Integer amountSold = 0;BigDecimal amountIncome = BigDecimal.valueOf(0);
    for(TransactionDetail detail: details){
      if(detail.getItemCode().equals(id)){
        amountIncome = amountIncome.add(detail.getSubTotalDec());
        amountSold += detail.getQuantity();
      }
    }
    return convertDataEntitiesToModels(Collections.singletonList(dataEntity)).get(0)
      .setTotalSold(amountSold)
      .setIncomeAmountDec(amountIncome)
      .setIncomeAmount(formatCurrency(amountIncome));

  }

  @Override
  public List<String> update(UpsertItemRequestPayload upsertItemRequestPayload) {
    List<String> stats = new LinkedList<>();
    if(itemDAO.update(convertUpsertPayloadToDataEntity(upsertItemRequestPayload)) == 1){
      stats.add(ITEM+ upsertItemRequestPayload.getName()+ SUCCESS+" "+ PostgresHelper.UPDATED);
    }else{
      stats.add(ITEM+ upsertItemRequestPayload.getName()+ FAIL+" "+ PostgresHelper.UPDATED);
    }
    return stats;
  }

  @Override
  public List<String> delete(String itemCode) {
    List<String> stats = new LinkedList<>();
    if(itemDAO.delete(itemCode) == 1){
      stats.add( ITEM+ itemCode + SUCCESS+" "+ PostgresHelper.REMOVED);
    }else{
      stats.add(ITEM+ itemCode + FAIL+" "+ PostgresHelper.REMOVED);
    }
    return stats;
  }
}

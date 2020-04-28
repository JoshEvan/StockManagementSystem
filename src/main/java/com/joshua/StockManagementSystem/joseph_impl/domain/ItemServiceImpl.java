package com.joshua.StockManagementSystem.joseph_impl.domain;

import com.joshua.StockManagementSystem.joseph_api.api.payload.UpsertItemRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.domain.ItemService;
import com.joshua.StockManagementSystem.joseph_api.infrastructure.dao.ItemDAO;
import com.joshua.StockManagementSystem.joseph_api.model.Item;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.PostgresHelper;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.adapter.CustomerAdapter;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.adapter.ItemAdapter;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout.CustomerDataEntity;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout.ItemDataEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static com.joshua.StockManagementSystem.joseph_impl.infrastructure.PostgresHelper.*;
import static com.joshua.StockManagementSystem.joseph_impl.infrastructure.adapter.ItemAdapter.convertDataEntitiesToModels;
import static com.joshua.StockManagementSystem.joseph_impl.infrastructure.adapter.ItemAdapter.convertUpsertPayloadToDataEntity;

@Component("itemV1Service")
@Service
public class ItemServiceImpl implements ItemService {
  private final ItemDAO itemDAO;

  @Autowired
  public ItemServiceImpl(ItemDAO itemDAO) {
    this.itemDAO = itemDAO;
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
    return convertDataEntitiesToModels(itemDAO.index());
  }

  @Override
  public Item show(String id) {
    ItemDataEntity dataEntity = itemDAO.show(id).orElse(null);
    return (dataEntity == null) ? null :
            convertDataEntitiesToModels(Collections.singletonList(dataEntity)).get(0);
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

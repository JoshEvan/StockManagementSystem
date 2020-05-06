package com.joshua.StockManagementSystem.joseph_api.domain;


import com.joshua.StockManagementSystem.joseph_api.api.payload.index.IndexItemRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.api.payload.upsert.UpsertItemRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.model.Item;

import java.util.List;

public interface ItemService {
  List<String> insert(UpsertItemRequestPayload upsertCustomerRequestPayload);
  List<Item> index(IndexItemRequestPayload indexItemRequestPayload);
  Item show(String id);
  List<String> update(UpsertItemRequestPayload upsertCustomerRequestPayload);
  List<String> delete(String itemCode);
  void generateReport(IndexItemRequestPayload indexItemRequestPayload);
}

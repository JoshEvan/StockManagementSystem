package com.joshua.StockManagementSystem.joseph_api.domain;


import com.joshua.StockManagementSystem.joseph_api.api.payload.index.IndexItemRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.api.payload.upsert.UpsertItemRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.model.Item;
import com.joshua.StockManagementSystem.util.Pair;

import java.util.List;

public interface ItemService {
  Pair<Boolean,List<String>> insert(UpsertItemRequestPayload upsertCustomerRequestPayload);
  List<Item> index(IndexItemRequestPayload indexItemRequestPayload);
  Item show(String id);
  Pair<Boolean,List<String>> update(UpsertItemRequestPayload upsertCustomerRequestPayload);
  Pair<Boolean,List<String>> delete(String itemCode);
  void generateReport(IndexItemRequestPayload indexItemRequestPayload);
}

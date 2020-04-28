package com.joshua.StockManagementSystem.joseph_api.domain;

import com.joshua.StockManagementSystem.joseph_api.api.payload.UpsertCustomerRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.model.Customer;

import java.util.List;

public interface ItemService {
  List<String> insertItem(UpsertCustomerRequestPayload upsertCustomerRequestPayload);
  List<Customer> indexItem();
  List<String> updateItem(UpsertCustomerRequestPayload upsertCustomerRequestPayload);
  List<String> deleteItem(String itemCode);
}

package com.joshua.StockManagementSystem.joseph_impl.domain;

import com.joshua.StockManagementSystem.joseph_api.api.payload.UpsertCustomerRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.domain.ItemService;
import com.joshua.StockManagementSystem.joseph_api.model.Customer;

import java.util.List;

public class ItemServiceImpl implements ItemService {
  @Override
  public List<String> insertItem(UpsertCustomerRequestPayload upsertCustomerRequestPayload) {
    return null;
  }

  @Override
  public List<Customer> indexItem() {
    return null;
  }

  @Override
  public List<String> updateItem(UpsertCustomerRequestPayload upsertCustomerRequestPayload) {
    return null;
  }

  @Override
  public List<String> deleteItem(String itemCode) {
    return null;
  }
}

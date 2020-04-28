package com.joshua.StockManagementSystem.joseph_api.domain;

import com.joshua.StockManagementSystem.joseph_api.api.payload.UpsertCustomerRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.model.Customer;

import java.util.List;

public interface JosephService {
  List<String> insertCustomer(UpsertCustomerRequestPayload upsertCustomerRequestPayload);
  List<Customer> indexCustomer();
  List<String> updateCustomer(UpsertCustomerRequestPayload upsertCustomerRequestPayload);
  List<String> deleteCustomer(String idCust);
}

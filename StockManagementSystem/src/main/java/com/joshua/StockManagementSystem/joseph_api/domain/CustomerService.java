package com.joshua.StockManagementSystem.joseph_api.domain;

import com.joshua.StockManagementSystem.joseph_api.api.payload.upsert.UpsertCustomerRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.model.Customer;
import javafx.util.Pair;

import java.util.List;

public interface CustomerService {
  Pair<Boolean,List<String>> insertCustomer(UpsertCustomerRequestPayload upsertCustomerRequestPayload);
  List<Customer> indexCustomer();
  Customer showCustomer(String id);
  List<String> updateCustomer(UpsertCustomerRequestPayload upsertCustomerRequestPayload);
  Pair<Boolean,List<String>> deleteCustomer(String id);
}

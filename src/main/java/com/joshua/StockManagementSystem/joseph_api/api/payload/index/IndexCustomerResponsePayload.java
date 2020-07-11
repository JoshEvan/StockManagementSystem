package com.joshua.StockManagementSystem.joseph_api.api.payload.index;

import com.joshua.StockManagementSystem.joseph_api.model.Customer;

import java.util.List;

public class IndexCustomerResponsePayload {
  List<Customer> customers;

  public List<Customer> getCustomers() {
    return customers;
  }

  public IndexCustomerResponsePayload setCustomers(List<Customer> customers) {
    this.customers = customers;return this;
  }
}

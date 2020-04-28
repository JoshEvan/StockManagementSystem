package com.joshua.StockManagementSystem.joseph_impl.api.spec;

import com.joshua.StockManagementSystem.joseph_api.model.Customer;

public class UpdateCustomerSpec {
  private Customer updatedCustomer;
  private String idCustomer;

  public UpdateCustomerSpec(Customer updatedCustomer, String idCustomer) {
    this.updatedCustomer = updatedCustomer;
    this.idCustomer = idCustomer;
  }

  public Customer getUpdatedCustomer() {
    return updatedCustomer;
  }

  public void setUpdatedCustomer(Customer updatedCustomer) {
    this.updatedCustomer = updatedCustomer;
  }

  public String getIdCustomer() {
    return idCustomer;
  }

  public void setIdCustomer(String idCustomer) {
    this.idCustomer = idCustomer;
  }
}

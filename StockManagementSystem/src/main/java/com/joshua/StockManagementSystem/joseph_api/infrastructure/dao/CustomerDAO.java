package com.joshua.StockManagementSystem.joseph_api.infrastructure.dao;

import com.joshua.StockManagementSystem.joseph_api.model.Customer;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout.CustomerDataEntity;

import java.util.List;

public interface CustomerDAO {
  Integer insertCustomer(CustomerDataEntity customer);
  List<CustomerDataEntity> indexCustomer();
  Integer updateCustomer(CustomerDataEntity customer);
  Integer deleteCustomer(String idCust);
}

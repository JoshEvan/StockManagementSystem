package com.joshua.StockManagementSystem.joseph_api.infrastructure.dao;

import com.joshua.StockManagementSystem.joseph_api.model.Customer;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout.CustomerDataEntity;

import java.util.List;
import java.util.Optional;

public interface CustomerDAO {
  Integer insertCustomer(CustomerDataEntity customer);
  List<CustomerDataEntity> indexCustomer();
  Optional<CustomerDataEntity> showCustomer(String id);
  Integer updateCustomer(CustomerDataEntity customer);
  Integer deleteCustomer(String idCust);
}

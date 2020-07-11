package com.joshua.StockManagementSystem.joseph_api.infrastructure.dao;

import com.joshua.StockManagementSystem.joseph_api.model.Customer;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout.CustomerDataEntity;

import java.util.List;
import java.util.Optional;

public interface CustomerDAO {
  Integer insert(CustomerDataEntity customer);
  List<CustomerDataEntity> index();
  Optional<CustomerDataEntity> show(String id);
  Integer update(CustomerDataEntity customer);
  Integer delete(String idCust);

}

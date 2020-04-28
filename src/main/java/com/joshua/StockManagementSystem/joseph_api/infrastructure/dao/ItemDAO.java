package com.joshua.StockManagementSystem.joseph_api.infrastructure.dao;

import com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout.ItemDataEntity;

import java.util.List;

public interface ItemDAO {
  Integer insert(ItemDataEntity customer);
  List<ItemDataEntity> index();
  Integer update(ItemDataEntity customer);
  Integer delete(String idItem);
}

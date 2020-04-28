package com.joshua.StockManagementSystem.joseph_api.infrastructure.dao;

import com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout.ItemDataEntity;

import java.util.List;
import java.util.Optional;

public interface ItemDAO {
  Integer insert(ItemDataEntity customer);
  List<ItemDataEntity> index();
  Optional<ItemDataEntity> show(String id);
  Integer update(ItemDataEntity customer);
  Integer delete(String idItem);
}

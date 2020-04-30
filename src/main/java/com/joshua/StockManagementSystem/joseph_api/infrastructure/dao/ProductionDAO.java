package com.joshua.StockManagementSystem.joseph_api.infrastructure.dao;

import com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout.ProductionDataEntity;

import java.util.List;
import java.util.Optional;

public interface ProductionDAO {
  Integer insert(ProductionDataEntity productionDataEntity);
  List<ProductionDataEntity> index();
  Optional<ProductionDataEntity> show(String id);
  Integer update(ProductionDataEntity productionDataEntity);
  Integer delete(String idItem);
}

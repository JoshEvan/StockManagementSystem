package com.joshua.StockManagementSystem.joseph_api.infrastructure.dao;


import com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout.PaymentDataEntity;

import java.util.List;
import java.util.Optional;

public interface PaymentDAO {
  Integer insert(PaymentDataEntity paymentDataEntity);
  List<PaymentDataEntity> index();
  Optional<PaymentDataEntity> show(String id);
  Integer update(PaymentDataEntity paymentDataEntity);
  Integer delete(String idItem);
}

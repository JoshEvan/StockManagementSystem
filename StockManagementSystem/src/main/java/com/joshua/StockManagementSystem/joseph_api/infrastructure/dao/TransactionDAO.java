package com.joshua.StockManagementSystem.joseph_api.infrastructure.dao;

import com.joshua.StockManagementSystem.joseph_api.api.payload.index.IndexTransactionRequestPayload;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.dao.spec.TransactionSpec;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout.ProductionDataEntity;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout.TransactionDetailDataEntity;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout.TransactionHeaderDataEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface TransactionDAO {
  Integer insert(TransactionHeaderDataEntity transactionHeaderDataEntity, List<TransactionDetailDataEntity> details);
  List<TransactionSpec> index(IndexTransactionRequestPayload indexTransactionRequestPayload);
  List<TransactionDetailDataEntity> indexDetails(IndexTransactionRequestPayload indexTransactionRequestPayload, HashMap<String,Integer> indexOfId);
  Optional<TransactionSpec> show(String id);
  Integer update(TransactionHeaderDataEntity transactionHeaderDataEntity, List<TransactionDetailDataEntity> details);
  Integer delete(String idItem);
}

package com.joshua.StockManagementSystem.joseph_impl.infrastructure.dao.spec;

import com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout.TransactionDetailDataEntity;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout.TransactionHeaderDataEntity;

import java.util.List;

public class TransactionSpec {
  private TransactionHeaderDataEntity transactionHeaderDataEntity;
  private List<TransactionDetailDataEntity> transactionDetailDataEntityList;

  public TransactionHeaderDataEntity getTransactionHeaderDataEntity() {
    return transactionHeaderDataEntity;
  }

  public TransactionSpec setTransactionHeaderDataEntity(TransactionHeaderDataEntity transactionHeaderDataEntity) {
    this.transactionHeaderDataEntity = transactionHeaderDataEntity;return this;
  }

  public List<TransactionDetailDataEntity> getTransactionDetailDataEntityList() {
    return transactionDetailDataEntityList;
  }

  public TransactionSpec setTransactionDetailDataEntityList(List<TransactionDetailDataEntity> transactionDetailDataEntityList) {
    this.transactionDetailDataEntityList = transactionDetailDataEntityList;return this;
  }
}

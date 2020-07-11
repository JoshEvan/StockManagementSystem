package com.joshua.StockManagementSystem.joseph_api.api.payload.index;

import com.joshua.StockManagementSystem.joseph_api.model.TransactionHeader;

import java.util.List;

public class IndexTransactionResponsePayload {
  List<TransactionHeader> transactions;

  public List<TransactionHeader> getTransactions() {
    return transactions;
  }

  public IndexTransactionResponsePayload setTransactions(List<TransactionHeader> transactions) {
    this.transactions = transactions; return this;
  }
}

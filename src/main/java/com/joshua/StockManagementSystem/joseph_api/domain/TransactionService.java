package com.joshua.StockManagementSystem.joseph_api.domain;

import com.joshua.StockManagementSystem.joseph_api.api.payload.index.IndexTransactionRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.api.payload.upsert.UpsertTransactionHeaderRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.model.TransactionHeader;

import java.util.List;

public interface TransactionService {
  List<String> insert(UpsertTransactionHeaderRequestPayload upsertTransactionHeaderRequestPayload);
  List<TransactionHeader> index(IndexTransactionRequestPayload indexTransactionRequestPayload);
  TransactionHeader show(String id);
  List<String> update(UpsertTransactionHeaderRequestPayload upsertTransactionHeaderRequestPayload);
  List<String> delete(String id);
}

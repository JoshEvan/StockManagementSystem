package com.joshua.StockManagementSystem.joseph_api.domain;

import com.joshua.StockManagementSystem.joseph_api.api.payload.index.IndexTransactionRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.api.payload.upsert.UpsertTransactionHeaderRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.model.TransactionHeader;
import com.sun.org.apache.xpath.internal.operations.Bool;
import com.joshua.StockManagementSystem.util.Pair;

import java.util.List;

public interface TransactionService {
  Pair<Boolean,List<String>> insert(UpsertTransactionHeaderRequestPayload upsertTransactionHeaderRequestPayload);
  List<TransactionHeader> index(IndexTransactionRequestPayload indexTransactionRequestPayload);
  TransactionHeader show(String id);
  Pair<Boolean,List<String>> update(UpsertTransactionHeaderRequestPayload upsertTransactionHeaderRequestPayload);
  Pair<Boolean,List<String>> delete(String id);
  void generateReport(IndexTransactionRequestPayload indexTransactionRequestPayload);
}

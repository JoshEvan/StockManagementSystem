package com.joshua.StockManagementSystem.joseph_api.domain;

import com.joshua.StockManagementSystem.joseph_api.api.payload.UpsertTransactionHeaderRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.model.TransactionHeader;

import java.util.List;

public interface TransactionService {
  List<String> insert(UpsertTransactionHeaderRequestPayload upsertTransactionHeaderRequestPayload);
  List<TransactionHeader> index();
  TransactionHeader show(String id);
  List<String> update(UpsertTransactionHeaderRequestPayload upsertTransactionHeaderRequestPayload);
  List<String> delete(String id);
}

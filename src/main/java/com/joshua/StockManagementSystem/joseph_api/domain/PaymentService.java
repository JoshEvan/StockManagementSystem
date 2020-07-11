package com.joshua.StockManagementSystem.joseph_api.domain;

import com.joshua.StockManagementSystem.joseph_api.api.payload.upsert.UpsertPaymentRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.model.Payment;
import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.util.Pair;

import java.util.List;

public interface PaymentService {
  Pair<Boolean,List<String>> insert(UpsertPaymentRequestPayload upsertPaymentRequestPayload);
  List<Payment> index();
  Payment show(String id);
  Pair<Boolean,List<String>> update(UpsertPaymentRequestPayload upsertPaymentRequestPayload);
  Pair<Boolean,List<String>> delete(String id);
}

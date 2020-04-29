package com.joshua.StockManagementSystem.joseph_api.domain;

import com.joshua.StockManagementSystem.joseph_api.api.payload.UpsertItemRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.api.payload.UpsertPaymentRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.model.Item;
import com.joshua.StockManagementSystem.joseph_api.model.Payment;

import java.util.List;

public interface PaymentService {
  List<String> insert(UpsertPaymentRequestPayload upsertPaymentRequestPayload);
  List<Payment> index();
  Payment show(String id);
  List<String> update(UpsertPaymentRequestPayload upsertPaymentRequestPayload);
  List<String> delete(String id);
}

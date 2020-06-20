package com.joshua.StockManagementSystem.joseph_api.api.payload.index;

import com.joshua.StockManagementSystem.joseph_api.model.Payment;

import java.util.List;

public class IndexPayTypeResponsePayload {
  List<Payment> paymentTypes;

  public List<Payment> getPaymentTypes() {
    return paymentTypes;
  }

  public IndexPayTypeResponsePayload setPaymentTypes(List<Payment> paymentTypes) {
    this.paymentTypes = paymentTypes;return this;
  }
}

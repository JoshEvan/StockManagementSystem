package com.joshua.StockManagementSystem.joseph_api.api.payload.upsert;

public class UpsertPaymentRequestPayload {
  private String id, paymentType;

  public String getId() {
    return id;
  }

  public UpsertPaymentRequestPayload setId(String id) {
    this.id = id;return this;
  }

  public String getPaymentType() {
    return paymentType;
  }

  public UpsertPaymentRequestPayload setPaymentType(String paymentType) {
    this.paymentType = paymentType;return this;
  }
}

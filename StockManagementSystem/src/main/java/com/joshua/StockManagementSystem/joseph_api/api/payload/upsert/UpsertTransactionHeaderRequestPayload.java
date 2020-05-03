package com.joshua.StockManagementSystem.joseph_api.api.payload.upsert;

import java.util.Date;
import java.util.List;

public class UpsertTransactionHeaderRequestPayload {
  private String id, customerId, paymentId;
  private Date transactionDate;
  private String paymentStatus, note;

  List<UpsertTransactionDetailRequestPayload> transactionDetails;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public String getPaymentId() {
    return paymentId;
  }

  public void setPaymentId(String paymentId) {
    this.paymentId = paymentId;
  }

  public Date getTransactionDate() {
    return transactionDate;
  }

  public void setTransactionDate(Date transactionDate) {
    this.transactionDate = transactionDate;
  }

  public String getPaymentStatus() {
    return paymentStatus;
  }

  public void setPaymentStatus(String paymentStatus) {
    this.paymentStatus = paymentStatus;
  }

  public List<UpsertTransactionDetailRequestPayload> getTransactionDetails() {
    return transactionDetails;
  }

  public UpsertTransactionHeaderRequestPayload setTransactionDetails(List<UpsertTransactionDetailRequestPayload> transactionDetails) {
    this.transactionDetails = transactionDetails;return this;
  }

  public String getNote() {
    return note;
  }

  public UpsertTransactionHeaderRequestPayload setNote(String note) {
    this.note = note;return this;
  }
}

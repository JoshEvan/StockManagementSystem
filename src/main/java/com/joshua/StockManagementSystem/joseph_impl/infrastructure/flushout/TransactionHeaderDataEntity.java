package com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout;

import java.util.Date;

public class TransactionHeaderDataEntity extends JosephDataEntity{
  private String id, customerId,
          paymentId;
  private Date transactionDate;
  private String paymentStatus, note;

  public static final String ID = "id",
          CUSTID = "customer_id", PAYID = "payment_id",
          TRANDATE = "transaction_date",
          PAYSTAT = "payment_status",
          NOTE = "note";

  public TransactionHeaderDataEntity() {
    TABLE = "transaction_headers";
    numColumns = 6;
  }

  public String getId() {
    return id;
  }

  public TransactionHeaderDataEntity setId(String id) {
    this.id = id;return this;
  }

  public String getCustomerId() {
    return customerId;
  }

  public TransactionHeaderDataEntity setCustomerId(String customerId) {
    this.customerId = customerId;return this;
  }

  public String getPaymentId() {
    return paymentId;
  }

  public TransactionHeaderDataEntity setPaymentId(String paymentId) {
    this.paymentId = paymentId;return this;
  }

  public Date getTransactionDate() {
    return transactionDate;
  }

  public TransactionHeaderDataEntity setTransactionDate(Date transactionDate) {
    this.transactionDate = transactionDate;return this;
  }

  public String getPaymentStatus() {
    return paymentStatus;
  }

  public TransactionHeaderDataEntity setPaymentStatus(String paymentStatus) {
    this.paymentStatus = paymentStatus;return this;
  }

  public String getNote() {
    return note;
  }

  public TransactionHeaderDataEntity setNote(String note) {
    this.note = note;return this;
  }
}

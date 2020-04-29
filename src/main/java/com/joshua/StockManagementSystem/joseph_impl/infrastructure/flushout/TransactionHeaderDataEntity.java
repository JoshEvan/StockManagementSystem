package com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout;

public class TransactionHeaderDataEntity extends JosephDataEntity{
  private String id, customer_id, payment_id, transaction_date, payment_status;

  public TransactionHeaderDataEntity() {
    TABLE = "transaction_headers";
    numColumns = 5;
  }

  public String getId() {
    return id;
  }

  public TransactionHeaderDataEntity setId(String id) {
    this.id = id;return this;
  }

  public String getCustomer_id() {
    return customer_id;
  }

  public TransactionHeaderDataEntity setCustomer_id(String customer_id) {
    this.customer_id = customer_id;return this;
  }

  public String getPayment_id() {
    return payment_id;
  }

  public TransactionHeaderDataEntity setPayment_id(String payment_id) {
    this.payment_id = payment_id;return this;
  }

  public String getTransaction_date() {
    return transaction_date;
  }

  public TransactionHeaderDataEntity setTransaction_date(String transaction_date) {
    this.transaction_date = transaction_date;return this;
  }

  public String getPayment_status() {
    return payment_status;
  }

  public TransactionHeaderDataEntity setPayment_status(String payment_status) {
    this.payment_status = payment_status;return this;
  }
}

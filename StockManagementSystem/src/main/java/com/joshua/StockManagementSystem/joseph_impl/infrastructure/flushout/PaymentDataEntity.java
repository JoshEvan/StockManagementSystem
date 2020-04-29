package com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout;

public class PaymentDataEntity extends JosephDataEntity {
  private String id, payment_type;
  public static String ID = "id", TYPE = "payment_type";

  public PaymentDataEntity() {
    TABLE = "payments";
    numColumns = 2;
  }

  public String getId() {
    return id;
  }

  public PaymentDataEntity setId(String id) {
    this.id = id;return this;
  }

  public String getPayment_type() {
    return payment_type;
  }

  public PaymentDataEntity setPayment_type(String payment_type) {
    this.payment_type = payment_type;return this;
  }

}

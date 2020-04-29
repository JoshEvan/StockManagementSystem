package com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout;

public class TransactionDetailDataEntity extends JosephDataEntity{
  private String transactionHeaderId, itemCode;
  private Float price;
  private Integer quantity;

  public String getTransactionHeaderId() {
    return transactionHeaderId;
  }

  public TransactionDetailDataEntity setTransactionHeaderId(String transactionHeaderId) {
    this.transactionHeaderId = transactionHeaderId;return this;
  }

  public String getItemCode() {
    return itemCode;
  }

  public TransactionDetailDataEntity setItemCode(String itemCode) {
    this.itemCode = itemCode;return this;
  }

  public Float getPrice() {
    return price;
  }

  public TransactionDetailDataEntity setPrice(Float price) {
    this.price = price;return this;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public TransactionDetailDataEntity setQuantity(Integer quantity) {
    this.quantity = quantity;return this;
  }
}

package com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class TransactionDetailDataEntity extends JosephDataEntity{
  private String transactionHeaderId, itemCode;
  private BigDecimal price;
  private Integer quantity;
  private String note;
  private Timestamp timestamp;

  public static final String TRANSHID = "transaction_header_id",
          ITEMCODE = "item_code", PRICE = "price",
          QTY = "quantity",
          NOTE = "note",
          TIMESTAMP = "timestamp";

  public TransactionDetailDataEntity() {
    TABLE = "transaction_details";
    numColumns = 5;
  }

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

  public BigDecimal getPrice() {
    return price;
  }

  public TransactionDetailDataEntity setPrice(BigDecimal price) {
    this.price = price;return this;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public TransactionDetailDataEntity setQuantity(Integer quantity) {
    this.quantity = quantity;return this;
  }

  public String getNote() {
    return note;
  }

  public TransactionDetailDataEntity setNote(String note) {
    this.note = note;return this;
  }

  public Timestamp getTimestamp() {
    return timestamp;
  }

  public TransactionDetailDataEntity setTimestamp(Timestamp timestamp) {
    this.timestamp = timestamp;return this;
  }
}

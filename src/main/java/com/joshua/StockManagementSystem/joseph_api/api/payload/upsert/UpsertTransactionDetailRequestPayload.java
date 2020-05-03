package com.joshua.StockManagementSystem.joseph_api.api.payload.upsert;

public class UpsertTransactionDetailRequestPayload {
  private String itemCode;
  private Integer quantity;
  private String note;

  public String getItemCode() {
    return itemCode;
  }

  public void setItemCode(String itemCode) {
    this.itemCode = itemCode;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public UpsertTransactionDetailRequestPayload setQuantity(Integer quantity) {
    this.quantity = quantity;return this;
  }

  public String getNote() {
    return note;
  }

  public UpsertTransactionDetailRequestPayload setNote(String note) {
    this.note = note;return this;
  }
}

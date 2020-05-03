package com.joshua.StockManagementSystem.joseph_api.api.payload.upsert;

import java.util.Date;

public class UpsertProductionRequestPayload {
  private String id, itemCode, producer;
  private Date productionDate;
  private int quantity;

  public UpsertProductionRequestPayload() {
  }

  public String getId() {
    return id;
  }

  public UpsertProductionRequestPayload setId(String id) {
    this.id = id;return this;
  }

  public String getItemCode() {
    return itemCode;
  }

  public UpsertProductionRequestPayload setItemCode(String itemCode) {
    this.itemCode = itemCode;return this;
  }

  public String getProducer() {
    return producer;
  }

  public UpsertProductionRequestPayload setProducer(String producer) {
    this.producer = producer;return this;
  }

  public Date getProductionDate() {
    return productionDate;
  }

  public UpsertProductionRequestPayload setProductionDate(Date productionDate) {
    this.productionDate = productionDate;return this;
  }

  public int getQuantity() {
    return quantity;
  }

  public UpsertProductionRequestPayload setQuantity(int quantity) {
    this.quantity = quantity;return this;
  }
}

package com.joshua.StockManagementSystem.joseph_api.api.payload.upsert;

import java.math.BigDecimal;

public class UpsertItemRequestPayload {
  private String itemCode, name, description;
  private BigDecimal price;
  private Integer stock, capacity;

  public String getItemCode() {
    return itemCode;
  }

  public UpsertItemRequestPayload setItemCode(String itemCode) {
    this.itemCode = itemCode;return this;
  }

  public String getName() {
    return name;
  }

  public UpsertItemRequestPayload setName(String name) {
    this.name = name;return this;
  }

  public String getDescription() {
    return description;
  }

  public UpsertItemRequestPayload setDescription(String description) {
    this.description = description;return this;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public UpsertItemRequestPayload setPrice(BigDecimal price) {
    this.price = price;return this;
  }

  public Integer getStock() {
    return stock;
  }

  public UpsertItemRequestPayload setStock(Integer stock) {
    this.stock = stock;return this;
  }

  public Integer getCapacity() {
    return capacity;
  }

  public UpsertItemRequestPayload setCapacity(Integer capacity) {
    this.capacity = capacity;return this;
  }
}

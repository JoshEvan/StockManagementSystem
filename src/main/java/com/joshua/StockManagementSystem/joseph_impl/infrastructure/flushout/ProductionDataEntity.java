package com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout;

public class ProductionDataEntity extends JosephDataEntity{
  private String id, item_code, production_date, producer;
  private Integer quantity;

  public String getId() {
    return id;
  }

  public ProductionDataEntity setId(String id) {
    this.id = id;return this;
  }

  public String getItem_code() {
    return item_code;
  }

  public ProductionDataEntity setItem_code(String item_code) {
    this.item_code = item_code;return this;
  }

  public String getProduction_date() {
    return production_date;
  }

  public ProductionDataEntity setProduction_date(String production_date) {
    this.production_date = production_date;return this;
  }

  public String getProducer() {
    return producer;
  }

  public ProductionDataEntity setProducer(String producer) {
    this.producer = producer;return this;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public ProductionDataEntity setQuantity(Integer quantity) {
    this.quantity = quantity;return this;
  }
}

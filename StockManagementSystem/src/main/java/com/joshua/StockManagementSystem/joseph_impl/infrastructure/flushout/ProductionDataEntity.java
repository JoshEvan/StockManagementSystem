package com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout;

import java.util.Date;

public class ProductionDataEntity extends JosephDataEntity{
  private String id, itemCode;
  private Date productionDate;
  private String producer;
  private Integer quantity;

  public static String ID = "id", ITEMCODE = "item_code", PRODDATE = "production_date", PRODUCER = "producer", QTY = "quantity";

  public ProductionDataEntity() {
    TABLE = "productions";
    numColumns = 5;
  }

  public String getId() {
    return id;
  }

  public ProductionDataEntity setId(String id) {
    this.id = id;return this;
  }

  public String getItemCode() {
    return itemCode;
  }

  public ProductionDataEntity setItemCode(String itemCode) {
    this.itemCode = itemCode;return this;
  }

  public Date getProductionDate() {
    return productionDate;
  }

  public ProductionDataEntity setProductionDate(Date productionDate) {
    this.productionDate = productionDate;return this;
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

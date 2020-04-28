package com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout;

public class ItemDataEntity extends JosephDataEntity{
  private String itemCode, name, description;
  private Float price;
  private Integer stock, capacity;

  public static String ITEMCODE = "item_code", NAME = "name", DESCRIPTION = "description",
  PRICE =  "price",STOCK = "stock",CAPACITY =  "capacity";

  public ItemDataEntity() {
    TABLE = "items";
    numColumns = 6;
  }

  public String getItemCode() {
    return itemCode;
  }

  public ItemDataEntity setItemCode(String itemCode) {
    this.itemCode = itemCode;return this;
  }

  public String getName() {
    return name;
  }

  public ItemDataEntity setName(String name) {
    this.name = name;return this;
  }

  public String getDescription() {
    return description;
  }

  public ItemDataEntity setDescription(String description) {
    this.description = description;return this;
  }

  public Float getPrice() {
    return price;
  }

  public ItemDataEntity setPrice(Float price) {
    this.price = price;return this;
  }

  public Integer getStock() {
    return stock;
  }

  public ItemDataEntity setStock(Integer stock) {
    this.stock = stock;return this;
  }

  public Integer getCapacity() {
    return capacity;
  }

  public ItemDataEntity setCapacity(Integer capacity) {
    this.capacity = capacity;return this;
  }
}

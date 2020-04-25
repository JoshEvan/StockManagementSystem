package com.joshua.StockManagementSystem.joseph_api.model;

public class Item {
    private String itemCode, name,description;
    private Float price;
    private Integer stock, capacity;

    public String getItemCode() {
        return itemCode;
    }

    public Item setItemCode(String itemCode) {
        this.itemCode = itemCode;return this;
    }

    public String getName() {
        return name;
    }

    public Item setName(String name) {
        this.name = name;return this;
    }

    public String getDescription() {
        return description;
    }

    public Item setDescription(String description) {
        this.description = description;return this;
    }

    public Float getPrice() {
        return price;
    }

    public Item setPrice(Float price) {
        this.price = price;return this;
    }

    public Integer getStock() {
        return stock;
    }

    public Item setStock(Integer stock) {
        this.stock = stock;return this;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public Item setCapacity(Integer capacity) {
        this.capacity = capacity;return this;
    }
}

package com.joshua.StockManagementSystem.joseph_api.model;

import com.joshua.StockManagementSystem.joseph_impl.infrastructure.PostgresHelper;

import java.math.BigDecimal;

public class Item {
    private String itemCode, name,description;
    private BigDecimal price;
    private Integer stock, capacity;
    private Integer totalSold;
    private BigDecimal incomeAmount;

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

    public BigDecimal getPriceDec() {
        return price;
    }

    public String getPrice() {
        return PostgresHelper.formatCurrency(price);
    }

    public Item setPrice(BigDecimal price) {
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

    public Integer getTotalSold() {
        return totalSold;
    }

    public Item setTotalSold(Integer totalSold) {
        this.totalSold = totalSold;return this;
    }

    public BigDecimal getIncomeAmount() {
        return incomeAmount;
    }

    public Item setIncomeAmount(BigDecimal incomeAmount) {
        this.incomeAmount = incomeAmount;return this;
    }
}

package com.joshua.StockManagementSystem.joseph_api.model;

public class TransactionDetail {
    private String transactionHeaderId, itemCode;
    private Float numeric;
    private Integer quantity;

    public String getTransactionHeaderId() {
        return transactionHeaderId;
    }

    public TransactionDetail setTransactionHeaderId(String transactionHeaderId) {
        this.transactionHeaderId = transactionHeaderId;return this;
    }

    public String getItemCode() {
        return itemCode;
    }

    public TransactionDetail setItemCode(String itemCode) {
        this.itemCode = itemCode;return this;
    }

    public Float getNumeric() {
        return numeric;
    }

    public TransactionDetail setNumeric(Float numeric) {
        this.numeric = numeric;return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public TransactionDetail setQuantity(Integer quantity) {
        this.quantity = quantity;return this;
    }
}

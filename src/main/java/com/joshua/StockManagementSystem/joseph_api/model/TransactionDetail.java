package com.joshua.StockManagementSystem.joseph_api.model;

public class TransactionDetail {
    private String transactionHeaderId, itemCode;
    private Float price;
    private Integer quantity;
    private String note;

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

    public Float getPrice() {
        return price;
    }

    public TransactionDetail setPrice(Float price) {
        this.price = price;return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public TransactionDetail setQuantity(Integer quantity) {
        this.quantity = quantity;return this;
    }

    public String getNote() {
        return note;
    }

    public TransactionDetail setNote(String note) {
        this.note = note;return this;
    }
}

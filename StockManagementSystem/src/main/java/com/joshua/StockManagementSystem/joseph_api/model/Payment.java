package com.joshua.StockManagementSystem.joseph_api.model;

public class Payment {
    private String id, paymentType;

    public String getId() {
        return id;
    }

    public Payment setId(String id) {
        this.id = id;return this;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public Payment setPaymentType(String paymentType) {
        this.paymentType = paymentType;return this;
    }
}

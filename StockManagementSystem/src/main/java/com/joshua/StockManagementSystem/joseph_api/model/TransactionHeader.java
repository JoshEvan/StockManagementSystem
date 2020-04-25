package com.joshua.StockManagementSystem.joseph_api.model;

public class TransactionHeader {
    private String id, customerId, paymentId;
    private String transactionDate;
    private String paymentStatus;

    public String getId() {
        return id;
    }

    public TransactionHeader setId(String id) {
        this.id = id;return this;
    }

    public String getCustomerId() {
        return customerId;
    }

    public TransactionHeader setCustomerId(String customerId) {
        this.customerId = customerId;return this;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public TransactionHeader setPaymentId(String paymentId) {
        this.paymentId = paymentId;return this;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public TransactionHeader setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;return this;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public TransactionHeader setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;return this;
    }
}

package com.joshua.StockManagementSystem.joseph_api.model;

import java.util.Date;
import java.util.List;

public class TransactionHeader {
    private String id, customerId, paymentId;
    private Date transactionDate;
    private String paymentStatus;
    private List<TransactionDetail> transactionDetails;

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

    public Date getTransactionDate() {
        return transactionDate;
    }

    public TransactionHeader setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;return this;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public TransactionHeader setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;return this;
    }

    public List<TransactionDetail> getTransactionDetails() {
        return transactionDetails;
    }

    public TransactionHeader setTransactionDetails(List<TransactionDetail> transactionDetails) {
        this.transactionDetails = transactionDetails;return this;
    }
}

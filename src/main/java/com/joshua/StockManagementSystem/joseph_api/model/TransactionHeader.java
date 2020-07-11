package com.joshua.StockManagementSystem.joseph_api.model;

import com.joshua.StockManagementSystem.joseph_impl.infrastructure.PostgresHelper;
import org.joda.money.BigMoney;
import org.joda.money.CurrencyUnit;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;

public class TransactionHeader {
    private String id, customerId, paymentId;
    private Date transactionDate;
    private String paymentStatus;
    private List<TransactionDetail> transactionDetails;
    private Date timestamp;
    private String note;
    private BigDecimal total;

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

    public Date getTimestamp() {
        return timestamp;
    }

    public TransactionHeader setTimestamp(Date timestamp) {
        this.timestamp = timestamp;return this;
    }

    public String getNote() {
        return note;
    }

    public TransactionHeader setNote(String note) {
        this.note = note;return this;
    }

    public String getTotal() {
        total =BigDecimal.valueOf(0);
        for(TransactionDetail detail: transactionDetails){
            total = total.add(detail.getSubTotalDec());
        }
       return PostgresHelper.formatCurrency(total);
    }

    public BigDecimal getTotalDec() {
        total =BigDecimal.valueOf(0);
        for(TransactionDetail detail: transactionDetails){
            total = total.add(detail.getSubTotalDec());
        }
        return total;
    }

    public TransactionHeader setTotal(BigDecimal total) {
        this.total = total;return this;
    }
}

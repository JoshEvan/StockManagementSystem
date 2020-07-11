package com.joshua.StockManagementSystem.joseph_api.model;

import com.joshua.StockManagementSystem.joseph_impl.infrastructure.PostgresHelper;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout.TransactionDetailDataEntity;
import org.joda.money.BigMoney;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.math.BigDecimal;
import java.util.Date;

public class TransactionDetail {
    private String transactionHeaderId, itemCode;
    private BigDecimal price;
    private Integer quantity;
    private String note;
    private Date timestamp;
    private BigDecimal subTotal;

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

    public String getPrice() {
        return PostgresHelper.formatCurrency(price);
    }

    public TransactionDetail setPrice(BigDecimal price) {
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

    public Date getTimestamp() {
        return timestamp;
    }

    public TransactionDetail setTimestamp(Date timestamp) {
        this.timestamp = timestamp;return this;
    }

    public String getSubTotal() {
        return PostgresHelper.formatCurrency(getSubTotalDec());
    }

    public BigDecimal getSubTotalDec() {
        return BigDecimal.valueOf(quantity).multiply(price);
    }

    public TransactionDetail setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;return this;
    }
}

package com.joshua.StockManagementSystem.joseph_api.api.payload.index;

import java.util.LinkedList;
import java.util.List;

public class IndexTransactionRequestPayload {
  private List<String> customerFilter = new LinkedList<>();
  private List<String> itemFilter = new LinkedList<>();
  private List<String> paymentFilter = new LinkedList<>();
  private List<String> transactionIdFilter = new LinkedList<>();
  private Integer sortByDate, sortByTotal;


  private String dateFilter = "";
  private String endDateFilter = "";

  public List<String> getCustomerFilter() {
    return customerFilter;
  }

  public IndexTransactionRequestPayload setCustomerFilter(List<String> customerFilter) {
    this.customerFilter = customerFilter;return this;
  }

  public List<String> getItemFilter() {
    return itemFilter;
  }

  public IndexTransactionRequestPayload setItemFilter(List<String> itemFilter) {
    this.itemFilter = itemFilter;return this;
  }

  public List<String> getPaymentFilter() {
    return paymentFilter;
  }

  public IndexTransactionRequestPayload setPaymentFilter(List<String> paymentFilter) {
    this.paymentFilter = paymentFilter;return this;
  }

  public String getDateFilter() {
    return dateFilter;
  }

  public IndexTransactionRequestPayload setDateFilter(String dateFilter) {
    this.dateFilter = dateFilter;return this;
  }

  public String getEndDateFilter() {
    return endDateFilter;
  }

  public IndexTransactionRequestPayload setEndDateFilter(String endDateFilter) {
    this.endDateFilter = endDateFilter;return this;
  }

  public List<String> getTransactionIdFilter() {
    return transactionIdFilter;
  }

  public IndexTransactionRequestPayload setTransactionIdFilter(List<String> transactionIdFilter) {
    this.transactionIdFilter = transactionIdFilter;return this;
  }

  public Integer getSortByDate() {
    return sortByDate;
  }

  public IndexTransactionRequestPayload setSortByDate(Integer sortByDate) {
    this.sortByDate = sortByDate;return this;
  }

  public Integer getSortByTotal() {
    return sortByTotal;
  }

  public IndexTransactionRequestPayload setSortByTotal(Integer sortByTotal) {
    this.sortByTotal = sortByTotal;return this;
  }
}

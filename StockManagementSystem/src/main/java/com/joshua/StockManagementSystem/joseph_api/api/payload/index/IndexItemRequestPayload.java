package com.joshua.StockManagementSystem.joseph_api.api.payload.index;

public class IndexItemRequestPayload {
  Integer sortByItemCode = 0;
  Integer sortByAmountSold = 0;
  Integer sortByAmountIncome = 0;

  public Integer getSortByAmountSold() {
    return sortByAmountSold;
  }

  public IndexItemRequestPayload setSortByAmountSold(Integer sortByAmountSold) {
    this.sortByAmountSold = sortByAmountSold;return this;
  }

  public Integer getSortByAmountIncome() {
    return sortByAmountIncome;
  }

  public IndexItemRequestPayload setSortByAmountIncome(Integer sortByAmountIncome) {
    this.sortByAmountIncome = sortByAmountIncome;return this;
  }

  public Integer getSortByItemCode() {
    return sortByItemCode;
  }

  public IndexItemRequestPayload setSortByItemCode(Integer sortByItemCode) {
    this.sortByItemCode = sortByItemCode;return this;
  }
}

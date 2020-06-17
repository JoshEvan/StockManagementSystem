package com.joshua.StockManagementSystem.joseph_api.api.payload;

import java.util.List;

public class ItemResponsePayload {
  private String status;
  private List<String> msg;

  public String getStatus() {
    return status;
  }

  public ItemResponsePayload setStatus(String status) {
    this.status = status;return this;
  }

  public List<String> getMsg() {
    return msg;
  }

  public ItemResponsePayload setMsg(List<String> msg) {
    this.msg = msg;return this;
  }
}

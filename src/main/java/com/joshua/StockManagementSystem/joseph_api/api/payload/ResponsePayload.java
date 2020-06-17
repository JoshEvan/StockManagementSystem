package com.joshua.StockManagementSystem.joseph_api.api.payload;

import java.util.List;

public class ResponsePayload {
  private String status;
  private List<String> msg;

  public String getStatus() {
    return status;
  }

  public ResponsePayload setStatus(String status) {
    this.status = status;return this;
  }

  public List<String> getMsg() {
    return msg;
  }

  public ResponsePayload setMsg(List<String> msg) {
    this.msg = msg;return this;
  }
}

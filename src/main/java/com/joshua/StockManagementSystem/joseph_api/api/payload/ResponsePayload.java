package com.joshua.StockManagementSystem.joseph_api.api.payload;

import java.util.List;

public class ResponsePayload {
  private String status;
  private List<String> message;

  public String getStatus() {
    return status;
  }

  public ResponsePayload setStatus(String status) {
    this.status = status;return this;
  }

  public List<String> getMessage() {
    return message;
  }

  public ResponsePayload setMessage(List<String> message) {
    this.message = message;return this;
  }
}

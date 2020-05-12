package com.joshua.StockManagementSystem.joseph_api.api.payload.delete;

import java.util.List;

public class DeleteItemResponsePayload {
  private String status;
  private List<String> msg;

  public String getStatus() {
    return status;
  }

  public DeleteItemResponsePayload setStatus(String status) {
    this.status = status;return this;
  }

  public List<String> getMsg() {
    return msg;
  }

  public DeleteItemResponsePayload setMsg(List<String> msg) {
    this.msg = msg;return this;
  }
}

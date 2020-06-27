package com.joshua.StockManagementSystem.auth.api.payload;

import com.joshua.StockManagementSystem.auth.api.model.UserInfo;

import java.util.List;

public class IndexUserRequestPayload {
  List<UserInfo> users;

  public List<UserInfo> getUsers() {
    return users;
  }

  public IndexUserRequestPayload setUsers(List<UserInfo> users) {
    this.users = users;return this;
  }
}

package com.joshua.StockManagementSystem.auth.api.payload;

public class InsertUserRequestPayload {
  private String username, password;

  public String getUsername() {
    return username;
  }

  public InsertUserRequestPayload setUsername(String username) {
    this.username = username;return this;
  }

  public String getPassword() {
    return password;
  }

  public InsertUserRequestPayload setPassword(String password) {
    this.password = password;return this;
  }
}

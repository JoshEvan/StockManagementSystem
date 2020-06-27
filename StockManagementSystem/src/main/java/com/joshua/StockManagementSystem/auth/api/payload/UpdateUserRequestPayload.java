package com.joshua.StockManagementSystem.auth.api.payload;

public class UpdateUserRequestPayload {
  private String username,password;

  public String getUsername() {
    return username;
  }

  public UpdateUserRequestPayload setUsername(String username) {
    this.username = username;return this;
  }

  public String getPassword() {
    return password;
  }

  public UpdateUserRequestPayload setPassword(String password) {
    this.password = password;return this;
  }
}

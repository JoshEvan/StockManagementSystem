package com.joshua.StockManagementSystem.jwt;

public class UsernamePasswordAuthRequestPayload {
  private String username, password;

  public UsernamePasswordAuthRequestPayload() {
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}

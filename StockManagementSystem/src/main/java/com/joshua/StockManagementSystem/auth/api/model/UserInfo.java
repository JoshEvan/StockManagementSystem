package com.joshua.StockManagementSystem.auth.api.model;

public class UserInfo {
  private String username, role;

  public String getUsername() {
    return username;
  }

  public UserInfo setUsername(String username) {
    this.username = username;return this;
  }

  public String getRole() {
    return role;
  }

  public UserInfo setRole(String role) {
    this.role = role;return this;
  }
}

package com.joshua.StockManagementSystem.auth.api.payload;

public class AuthorizeUserRequestPayload {
  private String username, role;

  public String getUsername() {
    return username;
  }

  public AuthorizeUserRequestPayload setUsername(String username) {
    this.username = username;return this;
  }

  public String getRole() {
    return role;
  }

  public AuthorizeUserRequestPayload setRole(String role) {
    this.role = role;return this;
  }
}

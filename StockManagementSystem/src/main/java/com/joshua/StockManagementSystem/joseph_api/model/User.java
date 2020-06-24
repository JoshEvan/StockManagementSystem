package com.joshua.StockManagementSystem.joseph_api.model;

public class User {
  private Long id, roleId;
  private String username, email, password;

  public Long getId() {
    return id;
  }

  public User setId(Long id) {
    this.id = id;return this;
  }

  public Long getRoleId() {
    return roleId;
  }

  public User setRoleId(Long roleId) {
    this.roleId = roleId;return this;
  }

  public String getUsername() {
    return username;
  }

  public User setUsername(String username) {
    this.username = username;return this;
  }

  public String getEmail() {
    return email;
  }

  public User setEmail(String email) {
    this.email = email;return this;
  }

  public String getPassword() {
    return password;
  }

  public User setPassword(String password) {
    this.password = password;return this;
  }
}

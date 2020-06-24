package com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout;

public class UserDataEntity extends JosephDataEntity{
  private Long id, roleId;
  private String username, email, password;
  public static String ID = "id", USERNAME = "username", EMAIL = "email", PASSWORD = "password", ROLEID = "role_id";

  public UserDataEntity() {
    TABLE = "users";
    numColumns = 5;
  }

  public Long getId() {
    return id;
  }

  public UserDataEntity setId(Long id) {
    this.id = id;return this;
  }

  public Long getRoleId() {
    return roleId;
  }

  public UserDataEntity setRoleId(Long roleId) {
    this.roleId = roleId;return this;
  }

  public String getUsername() {
    return username;
  }

  public UserDataEntity setUsername(String username) {
    this.username = username;return this;
  }

  public String getEmail() {
    return email;
  }

  public UserDataEntity setEmail(String email) {
    this.email = email;return this;
  }

  public String getPassword() {
    return password;
  }

  public UserDataEntity setPassword(String password) {
    this.password = password;return this;
  }
}

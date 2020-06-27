package com.joshua.StockManagementSystem.auth.impl.infra.flushout;

import com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout.JosephDataEntity;

public class UserDataEntity extends JosephDataEntity {
  private String username, password, role;
  private Boolean isAccountNonExpired, isAccountNonLocked, isCredentialNonExpired, isEnabled;
  public static String USERNAME = "username", PASSWORD = "password", ROLE = "role",
  ACCEXP = "is_account_non_expired", ACCLOCK="is_account_non_locked",
  CREDEXP="is_credential_non_expired",ABLE="is_enabled";

  public UserDataEntity() {
    TABLE = "users";
    numColumns = 7;
  }

  public String getUsername() {
    return username;
  }

  public UserDataEntity setUsername(String username) {
    this.username = username;return this;
  }

  public String getPassword() {
    return password;
  }

  public UserDataEntity setPassword(String password) {
    this.password = password;return this;
  }

  public String getRole() {
    return role;
  }

  public UserDataEntity setRole(String role) {
    this.role = role;return this;
  }

  public Boolean getAccountNonExpired() {
    return isAccountNonExpired;
  }

  public UserDataEntity setAccountNonExpired(Boolean accountNonExpired) {
    isAccountNonExpired = accountNonExpired;return this;
  }

  public Boolean getAccountNonLocked() {
    return isAccountNonLocked;
  }

  public UserDataEntity setAccountNonLocked(Boolean accountNonLocked) {
    isAccountNonLocked = accountNonLocked;return this;
  }

  public Boolean getCredentialNonExpired() {
    return isCredentialNonExpired;
  }

  public UserDataEntity setCredentialNonExpired(Boolean credentialNonExpired) {
    isCredentialNonExpired = credentialNonExpired;return this;
  }

  public Boolean getEnabled() {
    return isEnabled;
  }

  public UserDataEntity setEnabled(Boolean enabled) {
    isEnabled = enabled; return this;
  }
}

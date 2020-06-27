package com.joshua.StockManagementSystem.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

public class User implements UserDetails {

  private final Set<? extends GrantedAuthority> grantedAuthorities;
  private final String password, username;
  private final boolean isAccountNonExpired,
          isAccountNonLocked,
          isCredentialNonExpired,
          isEnabled;

  /**
   *
   * @param grantedAuthorities
   * @param password
   * @param username
   * @param isAccountNonExpired
   * @param isAccountNonLocked
   * @param isCredentialNonExpired
   * @param isEnabled
   */
  public User(Set<? extends GrantedAuthority> grantedAuthorities, String password, String username, boolean isAccountNonExpired, boolean isAccountNonLocked, boolean isCredentialNonExpired, boolean isEnabled) {
    this.grantedAuthorities = grantedAuthorities;
    this.password = password;
    this.username = username;
    this.isAccountNonExpired = isAccountNonExpired;
    this.isAccountNonLocked = isAccountNonLocked;
    this.isCredentialNonExpired = isCredentialNonExpired;
    this.isEnabled = isEnabled;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return grantedAuthorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return isAccountNonExpired;
  }

  @Override
  public boolean isAccountNonLocked() {
    return isAccountNonLocked;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return isCredentialNonExpired;
  }

  @Override
  public boolean isEnabled() {
    return isEnabled;
  }
}
package com.joshua.StockManagementSystem.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum UserRole {
  /**
   * Sets.newHashSet() -> comes from Google Guava
   */
  STRANGER(Sets.newHashSet()),

  MEMBER(Sets.newHashSet(
          UserPermission.PROD_READ,
          UserPermission.PROD_WRITE)),

  ADMIN(Sets.newHashSet(
          UserPermission.TRANS_READ,
          UserPermission.TRANS_WRITE,
          UserPermission.ITEM_READ,
          UserPermission.ITEM_WRITE,
          UserPermission.PROD_READ,
          UserPermission.PROD_WRITE,
          UserPermission.CUST_READ,
          UserPermission.CUST_WRITE,
          UserPermission.PAYTYPE_READ,
          UserPermission.PAYTYPE_WRITE));

  /**
   * must be unique therefore using Set
   */
  private final Set<UserPermission> permissions;

  UserRole(Set<UserPermission> permissions) {
    this.permissions = permissions;
  }

  public Set<UserPermission> getPermissions() {
    return permissions;
  }

  public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
    Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
            .map(p -> new SimpleGrantedAuthority(p.getPermission()))
            .collect(Collectors.toSet());
    permissions.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
    return permissions;
  }

  public static UserRole whichUserRole(String role){
    if(isAdmin(role)) return ADMIN;
    if(isMember(role)) return MEMBER;
    return STRANGER;
  }

  public static boolean isAdmin(String s){
    return ADMIN.equals(s);
  }
  public static boolean isMember(String s){
    return MEMBER.equals(s);
  }
  public static boolean isStranger(String s){
    return STRANGER.equals(s);
  }


}

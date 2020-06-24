package com.joshua.StockManagementSystem.security;

import com.google.common.collect.Sets;

import java.util.Set;

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
}

package com.joshua.StockManagementSystem.security;

public enum UserPermission {
  TRANS_WRITE("transaction:write"),
  TRANS_READ("transaction:read"),
  PROD_READ("production:read"),
  PROD_WRITE("production:write"),
  CUST_READ("customer:read"),
  CUST_WRITE("customer:write"),
  ITEM_READ("item:read"),
  ITEM_WRITE("item:write"),
  PAYTYPE_READ("paytype:read"),
  PAYTYPE_WRITE("paytype:write");

  private final String permission;

  UserPermission(String permission) {
    this.permission = permission;
  }

  public String getPermission() {
    return permission;
  }
}

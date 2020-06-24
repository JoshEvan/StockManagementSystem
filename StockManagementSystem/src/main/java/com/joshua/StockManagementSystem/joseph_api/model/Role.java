package com.joshua.StockManagementSystem.joseph_api.model;

public class Role {
  private Long id;
  private RoleName roleName;

  public Long getId() {
    return id;
  }

  public Role setId(Long id) {
    this.id = id;return this;
  }

  public RoleName getRoleName() {
    return roleName;
  }

  public Role setRoleName(RoleName roleName) {
    this.roleName = roleName;return this;
  }
}

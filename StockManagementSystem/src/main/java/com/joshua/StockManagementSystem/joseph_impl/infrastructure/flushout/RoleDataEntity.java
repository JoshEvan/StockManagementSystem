package com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout;

import com.joshua.StockManagementSystem.joseph_api.model.RoleName;

public class RoleDataEntity extends JosephDataEntity {
  private Long id;
  private RoleName roleName;
  public static String ID = "id", ROLENAME = "role_name";

  public RoleDataEntity() {
    TABLE = "roles";
    numColumns = 2;
  }

  public Long getId() {
    return id;
  }

  public RoleDataEntity setId(Long id) {
    this.id = id;return this;
  }

  public RoleName getRoleName() {
    return roleName;
  }

  public RoleDataEntity setRoleName(RoleName roleName) {
    this.roleName = roleName;return this;
  }
}

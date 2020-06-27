package com.joshua.StockManagementSystem.auth.impl.infra.adapter;

import com.joshua.StockManagementSystem.auth.api.model.User;
import com.joshua.StockManagementSystem.auth.impl.infra.flushout.UserDataEntity;
import com.joshua.StockManagementSystem.security.UserRole;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAdapter {
  public static UserDataEntity convertResultSetToDataEntity(ResultSet resultSet) {
    try {
      return new UserDataEntity()
              .setUsername(resultSet.getString(UserDataEntity.USERNAME))
              .setPassword(resultSet.getString(UserDataEntity.PASSWORD))
              .setRole(resultSet.getString(UserDataEntity.ROLE))
              .setAccountNonExpired(resultSet.getBoolean(UserDataEntity.ACCEXP))
              .setAccountNonLocked(resultSet.getBoolean(UserDataEntity.ACCLOCK))
              .setCredentialNonExpired(resultSet.getBoolean(UserDataEntity.CREDEXP))
              .setEnabled(resultSet.getBoolean(UserDataEntity.ABLE));
    } catch (SQLException throwables) {
      return new UserDataEntity();
    }
  }

  public static User convertDataEntityToModel(UserDataEntity dataEntity) {
    return new User(
      UserRole.whichUserRole(dataEntity.getRole()).getGrantedAuthorities(),
      dataEntity.getPassword(),
      dataEntity.getUsername(),
      dataEntity.getAccountNonExpired(),
      dataEntity.getAccountNonLocked(),
      dataEntity.getCredentialNonExpired(),
      dataEntity.getEnabled()
    );
  }
}

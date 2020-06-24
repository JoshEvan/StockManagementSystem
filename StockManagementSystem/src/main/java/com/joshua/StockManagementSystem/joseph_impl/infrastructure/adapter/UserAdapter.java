package com.joshua.StockManagementSystem.joseph_impl.infrastructure.adapter;

import com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout.CustomerDataEntity;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout.UserDataEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAdapter {
  public static UserDataEntity convertResultSetToDataEntity(ResultSet resultSet) {
    try {
      return new UserDataEntity()
              .setId(resultSet.getLong(UserDataEntity.ID))
              .setUsername(resultSet.getString(UserDataEntity.USERNAME))
              .setEmail(resultSet.getString(UserDataEntity.EMAIL))
              .setPassword(resultSet.getString(UserDataEntity.PASSWORD));
    } catch (SQLException throwables) {
      return new UserDataEntity();
    }
  }
}

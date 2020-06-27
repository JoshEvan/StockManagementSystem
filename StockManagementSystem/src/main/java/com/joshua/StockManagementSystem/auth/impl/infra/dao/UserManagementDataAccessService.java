package com.joshua.StockManagementSystem.auth.impl.infra.dao;

import com.joshua.StockManagementSystem.auth.api.dao.UserManagementDAO;
import com.joshua.StockManagementSystem.auth.impl.infra.flushout.UserDataEntity;

public class UserManagementDataAccessService implements UserManagementDAO {
  @Override
  public Integer authorize(UserDataEntity userDataEntity) {
    return null;
  }

  @Override
  public Integer delete(String username) {
    return null;
  }
}

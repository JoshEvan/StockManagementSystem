package com.joshua.StockManagementSystem.auth.api.dao;

import com.joshua.StockManagementSystem.auth.impl.infra.flushout.UserDataEntity;

public interface UserManagementDAO {
  public Integer authorize(UserDataEntity userDataEntity);
  public Integer delete(String username);
}

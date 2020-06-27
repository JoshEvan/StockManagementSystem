package com.joshua.StockManagementSystem.auth.api.dao;

import com.joshua.StockManagementSystem.auth.api.model.User;
import com.joshua.StockManagementSystem.auth.impl.infra.flushout.UserDataEntity;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
  public Optional<User> selectUserByUsername(String username);
  public List<User> index();
  public Integer register(UserDataEntity userDataEntity);
  public Integer update(UserDataEntity userDataEntity);
}

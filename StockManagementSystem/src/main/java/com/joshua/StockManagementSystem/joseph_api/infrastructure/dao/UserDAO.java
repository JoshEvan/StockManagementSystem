package com.joshua.StockManagementSystem.joseph_api.infrastructure.dao;

import com.joshua.StockManagementSystem.joseph_api.model.User;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout.UserDataEntity;

import java.util.Optional;

public interface UserDAO {
  Optional<UserDataEntity> findByEmail(String email);
}

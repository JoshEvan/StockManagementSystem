package com.joshua.StockManagementSystem.joseph_api.infrastructure.dao;

import com.joshua.StockManagementSystem.auth.impl.infra.flushout.UserDataEntity;

import java.util.Optional;

public interface UserDAO {
  Optional<UserDataEntity> findByEmail(String email);
}

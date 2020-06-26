package com.joshua.StockManagementSystem.auth;

import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserDAO {
  public Optional<User> selectUserByUsername(String username);
}

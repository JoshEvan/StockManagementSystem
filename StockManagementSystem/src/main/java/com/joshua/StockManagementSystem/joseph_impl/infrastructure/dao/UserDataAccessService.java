package com.joshua.StockManagementSystem.joseph_impl.infrastructure.dao;

import com.joshua.StockManagementSystem.joseph_api.infrastructure.dao.UserDAO;
import com.joshua.StockManagementSystem.joseph_api.model.User;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.PostgresHelper;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.adapter.CustomerAdapter;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.adapter.UserAdapter;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout.CustomerDataEntity;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout.UserDataEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Optional;

public class UserDataAccessService implements UserDAO {
  private final JdbcTemplate jdbcTemplate;
  private final Logger log = LoggerFactory.getLogger(CustomerDataAccessService.class);

  @Autowired
  public UserDataAccessService(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public Optional<UserDataEntity> findByEmail(String email) {
    final String sql = PostgresHelper.selectOperation(new UserDataEntity())
            + " WHERE "+UserDataEntity.EMAIL +" = ?";

    try{
      UserDataEntity c =  jdbcTemplate.queryForObject(sql, new Object[]{email}, ((resultSet, i) -> {
        return UserAdapter.convertResultSetToDataEntity(resultSet);
      }));
      return Optional.ofNullable(c);
    }catch (Exception e){
      log.error("no user with email"+email);return null;
    }
  }

}

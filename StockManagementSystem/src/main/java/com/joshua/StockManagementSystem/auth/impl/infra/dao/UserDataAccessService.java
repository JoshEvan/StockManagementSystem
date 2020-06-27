package com.joshua.StockManagementSystem.auth.impl.infra.dao;

import com.google.common.collect.Lists;
import com.joshua.StockManagementSystem.auth.api.model.User;
import com.joshua.StockManagementSystem.auth.api.dao.UserDAO;
import com.joshua.StockManagementSystem.auth.impl.infra.adapter.UserAdapter;
import com.joshua.StockManagementSystem.auth.impl.infra.flushout.UserDataEntity;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.PostgresHelper;
import com.joshua.StockManagementSystem.security.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository("userPostgre")
public class UserDataAccessService implements UserDAO {

  private final PasswordEncoder passwordEncoder;

  private final JdbcTemplate jdbcTemplate;
  private final Logger logger = LoggerFactory.getLogger(UserDataAccessService.class);

  @Autowired
  public UserDataAccessService(PasswordEncoder passwordEncoder, JdbcTemplate jdbcTemplate) {
    this.passwordEncoder = passwordEncoder;
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public Optional<User> selectUserByUsername(String username) {
    List<User> users = index();
    Optional<User> found = users
            .stream()
            .filter(
                    appUser -> username.equals(appUser.getUsername())
            ).findFirst();
    return found;
  }

  @Override
  public List<User> index() {
    final String sql = PostgresHelper.selectOperation(new UserDataEntity())
            + " WHERE "+ UserDataEntity.ABLE+" = true";
    return jdbcTemplate.query(
            sql, ((resultSet, i) -> {
              UserDataEntity dataEntity = UserAdapter.convertResultSetToDataEntity(resultSet);
              User user = UserAdapter.convertDataEntityToModel(dataEntity);
              return user;
            })
    );
  }

  private List<User> getAppUsers(){
    List<User> users = Lists.newArrayList(
            new User(
                UserRole.MEMBER.getGrantedAuthorities(),
                passwordEncoder.encode("member1"),
                "member1",
                true,true,true,true
            ),
            new User(
                UserRole.ADMIN.getGrantedAuthorities(),
                passwordEncoder.encode("admin1"),
                "admin1",
                true,true,true,true
            ),new User(
                UserRole.STRANGER.getGrantedAuthorities(),
                passwordEncoder.encode("stranger1"),
                "stranger1",
                true,true,true,true
            )
    );
    return users;
  }

  @Override
  public Integer register(UserDataEntity userDataEntity) {
    final String sql = PostgresHelper.insertOperation(userDataEntity);
    return jdbcTemplate.update(sql
            ,userDataEntity.getUsername()
            ,userDataEntity.getPassword()
            ,userDataEntity.getRole()
            ,userDataEntity.getAccountNonExpired()
            ,userDataEntity.getAccountNonLocked()
            ,userDataEntity.getCredentialNonExpired()
            ,userDataEntity.getEnabled()
    );
  }

  @Override
  public Integer promote(UserDataEntity userDataEntity) {
    HashMap<String,Object> setter = new HashMap<>();
    setter.put(UserDataEntity.ROLE, userDataEntity.getRole());
    final String sql = PostgresHelper.updateOperation(userDataEntity,setter,UserDataEntity.USERNAME+" = \'" +userDataEntity.getUsername()+"\'");
    return jdbcTemplate.update(sql);
  }

  @Override
  public Integer delete(String username) {
    User user = selectUserByUsername(username).orElse(null);
    if(user == null) return 0;
    final String sql =
            PostgresHelper.deleteOperation(new UserDataEntity(),
            "WHERE "+UserDataEntity.USERNAME+" = \'"+username+"\'");
    return jdbcTemplate.update(sql);
  }
}

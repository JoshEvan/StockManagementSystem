package com.joshua.StockManagementSystem.auth;

import com.google.common.collect.Lists;
import com.joshua.StockManagementSystem.security.UserPermission;
import com.joshua.StockManagementSystem.security.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("userPostgre")
public class UserDataAccessService implements UserDAO{
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public UserDataAccessService(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public Optional<User> selectUserByUsername(String username) {
    List<User> users = getAppUsers();
    Optional<User> found = users
            .stream()
            .filter(
                    appUser -> username.equals(appUser.getUsername())
            ).findFirst();
    return found;
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
}
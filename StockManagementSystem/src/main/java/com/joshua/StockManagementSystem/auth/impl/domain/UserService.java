package com.joshua.StockManagementSystem.auth.impl.domain;

import com.joshua.StockManagementSystem.auth.api.dao.UserDAO;
import com.joshua.StockManagementSystem.auth.api.payload.InsertUserRequestPayload;
import com.joshua.StockManagementSystem.auth.api.payload.UpdateUserRequestPayload;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService, com.joshua.StockManagementSystem.auth.api.domain.UserService {

  private final UserDAO userDAO;

  @Autowired
  public UserService(@Qualifier("userPostgre") UserDAO userDAO) {
    this.userDAO = userDAO;
  }

  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    return userDAO.selectUserByUsername(s)
            .orElseThrow(
                    () ->
                    new UsernameNotFoundException("Username "+s+"Not Found"));
  }


  @Override
  public Pair<Boolean, List<String>> register(InsertUserRequestPayload insertUserRequestPayload) {
    return null;
  }

  @Override
  public Pair<Boolean, List<String>> update(UpdateUserRequestPayload updateUserRequestPayload) {
    return null;
  }
}

package com.joshua.StockManagementSystem.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

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
}

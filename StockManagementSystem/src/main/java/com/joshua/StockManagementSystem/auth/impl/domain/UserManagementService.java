package com.joshua.StockManagementSystem.auth.impl.domain;

import com.joshua.StockManagementSystem.auth.api.payload.AuthorizeUserRequestPayload;
import javafx.util.Pair;

import java.util.List;

public class UserManagementService implements com.joshua.StockManagementSystem.auth.api.domain.UserManagementService {
  @Override
  public Pair<Boolean, List<String>> authorize(AuthorizeUserRequestPayload authorizeUserRequestPayload) {
    return null;
  }

  @Override
  public Pair<Boolean, List<String>> delete(String username) {
    return null;
  }
}

package com.joshua.StockManagementSystem.auth.api.domain;

import com.joshua.StockManagementSystem.auth.api.payload.AuthorizeUserRequestPayload;
import javafx.util.Pair;

import java.util.List;

public interface UserManagementService {
  public Pair<Boolean, List<String>> authorize(AuthorizeUserRequestPayload authorizeUserRequestPayload);
  public Pair<Boolean,List<String>> delete(String username);
}

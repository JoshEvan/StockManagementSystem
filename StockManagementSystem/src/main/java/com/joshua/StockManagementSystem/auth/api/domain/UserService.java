package com.joshua.StockManagementSystem.auth.api.domain;

import com.joshua.StockManagementSystem.auth.api.payload.InsertUserRequestPayload;
import com.joshua.StockManagementSystem.auth.api.payload.UpdateUserRequestPayload;
import javafx.util.Pair;

import java.util.List;

public interface UserService {
  public Pair<Boolean, List<String>> register(InsertUserRequestPayload insertUserRequestPayload);
  public Pair<Boolean, List<String>> update(UpdateUserRequestPayload updateUserRequestPayload);
}

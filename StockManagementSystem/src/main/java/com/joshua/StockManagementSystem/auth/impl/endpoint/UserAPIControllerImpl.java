package com.joshua.StockManagementSystem.auth.impl.endpoint;

import com.joshua.StockManagementSystem.auth.api.endpoint.UserAPIController;
import com.joshua.StockManagementSystem.auth.api.model.UserInfo;
import com.joshua.StockManagementSystem.auth.api.payload.InsertUserRequestPayload;
import com.joshua.StockManagementSystem.auth.api.payload.AuthorizeUserRequestPayload;
import com.joshua.StockManagementSystem.auth.api.payload.UpdateUserRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.api.payload.ResponsePayload;

import javax.validation.constraints.NotNull;

public class UserAPIControllerImpl implements UserAPIController {

  @Override
  public UserInfo show(@NotNull String username) {
    return null;
  }

  @Override
  public ResponsePayload register(@NotNull InsertUserRequestPayload insertUserRequestPayload) {
    return null;
  }

  @Override
  public ResponsePayload update(@NotNull UpdateUserRequestPayload updateUserRequestPayload) {
    return null;
  }

}

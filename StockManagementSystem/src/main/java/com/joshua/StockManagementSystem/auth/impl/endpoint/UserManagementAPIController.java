package com.joshua.StockManagementSystem.auth.impl.endpoint;

import com.joshua.StockManagementSystem.auth.api.payload.AuthorizeUserRequestPayload;
import com.joshua.StockManagementSystem.auth.api.payload.IndexUserRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.api.payload.ResponsePayload;

import javax.validation.constraints.NotNull;

public class UserManagementAPIController implements com.joshua.StockManagementSystem.auth.api.endpoint.UserManagementAPIController {
  @Override
  public IndexUserRequestPayload index() {
    return null;
  }

  @Override
  public ResponsePayload authorize(@NotNull AuthorizeUserRequestPayload authorizeUserRequestPayload) {
    return null;
  }

  @Override
  public ResponsePayload delete(@NotNull String username) {
    return null;
  }
}

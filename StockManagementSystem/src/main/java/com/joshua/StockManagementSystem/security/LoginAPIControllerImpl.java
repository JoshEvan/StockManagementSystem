package com.joshua.StockManagementSystem.security;

import com.joshua.StockManagementSystem.joseph_api.api.payload.index.IndexProductionRequestPayload;

public class LoginAPIControllerImpl implements LoginAPIController {

  @Override
  public String getloginView() {
    return "login";
  }
}

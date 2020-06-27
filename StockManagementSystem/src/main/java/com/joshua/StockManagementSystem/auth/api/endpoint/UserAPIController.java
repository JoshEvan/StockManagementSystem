package com.joshua.StockManagementSystem.auth.api.endpoint;

import com.joshua.StockManagementSystem.auth.api.model.UserInfo;
import com.joshua.StockManagementSystem.auth.api.payload.IndexUserRequestPayload;
import com.joshua.StockManagementSystem.auth.api.payload.InsertUserRequestPayload;
import com.joshua.StockManagementSystem.auth.api.payload.AuthorizeUserRequestPayload;
import com.joshua.StockManagementSystem.auth.api.payload.UpdateUserRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.api.payload.ResponsePayload;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@CrossOrigin(origins="*",allowedHeaders = "*")
@RequestMapping("/api/v1/auth/user")
@RestController
@EnableAutoConfiguration
@Component("userAPIV1")
public interface UserAPIController {

  @GetMapping("/show/{id}")
  public UserInfo show(@NotNull @PathVariable("id") String username);

  @PostMapping("/register")
  public ResponsePayload register(@NotNull @RequestBody InsertUserRequestPayload insertUserRequestPayload);

  @PutMapping("/update")
  public ResponsePayload update(@NotNull @RequestBody UpdateUserRequestPayload updateUserRequestPayload);


}

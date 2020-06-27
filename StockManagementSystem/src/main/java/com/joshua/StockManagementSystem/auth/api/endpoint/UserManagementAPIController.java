package com.joshua.StockManagementSystem.auth.api.endpoint;

import com.joshua.StockManagementSystem.auth.api.payload.AuthorizeUserRequestPayload;
import com.joshua.StockManagementSystem.auth.api.payload.IndexUserRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.api.payload.ResponsePayload;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@CrossOrigin(origins="*",allowedHeaders = "*")
@RequestMapping("/api/v1/auth/role")
@RestController
@EnableAutoConfiguration
@Component("userAPIV1")
public interface UserManagementAPIController {

  @GetMapping("/index")
  public IndexUserRequestPayload index();

  @PutMapping("/update")
  public ResponsePayload authorize(@NotNull @RequestBody AuthorizeUserRequestPayload authorizeUserRequestPayload);

  @DeleteMapping("/delete/{id}")
  public ResponsePayload delete(@NotNull @PathVariable("id") String username);
}

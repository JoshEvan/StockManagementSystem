package com.joshua.StockManagementSystem.security;

import com.joshua.StockManagementSystem.joseph_api.api.payload.index.IndexProductionRequestPayload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public interface LoginAPIController {
  @PostMapping("login")
 String getloginView();
}

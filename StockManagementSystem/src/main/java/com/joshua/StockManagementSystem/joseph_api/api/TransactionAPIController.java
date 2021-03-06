package com.joshua.StockManagementSystem.joseph_api.api;

import com.joshua.StockManagementSystem.joseph_api.api.payload.ResponsePayload;
import com.joshua.StockManagementSystem.joseph_api.api.payload.index.IndexTransactionRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.api.payload.index.IndexTransactionResponsePayload;
import com.joshua.StockManagementSystem.joseph_api.api.payload.upsert.UpsertTransactionHeaderRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.model.TransactionHeader;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/v1/joseph/transaction")
@RestController
@EnableAutoConfiguration
@Component("transactionV1API")
public interface TransactionAPIController {
  @PostMapping("/insert")
  public ResponsePayload insert(@NotNull @RequestBody UpsertTransactionHeaderRequestPayload upsertTransactionHeaderRequestPayload);

  @PostMapping(value = "/", produces = "application/json")
  public @ResponseBody
  IndexTransactionResponsePayload index(@NotNull @RequestBody IndexTransactionRequestPayload indexTransactionRequestPayload);

  @GetMapping(value = "/show/{id}", produces = "application/json")
  public @ResponseBody TransactionHeader show(@NotNull @PathVariable("id") String id);

  @PutMapping("/update")
  public ResponsePayload update(@NotNull @RequestBody UpsertTransactionHeaderRequestPayload upsertTransactionHeaderRequestPayload);

  @DeleteMapping("/delete/{id}")
  public ResponsePayload delete(@NotNull @PathVariable("id") String id);

  @PostMapping("/report")
  public @ResponseBody  void generateReport(
          HttpServletResponse response,
          @NotNull @RequestBody IndexTransactionRequestPayload indexTransactionRequestPayload) throws IOException;

}

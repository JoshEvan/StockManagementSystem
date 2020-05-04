package com.joshua.StockManagementSystem.joseph_api.api;

import com.joshua.StockManagementSystem.joseph_api.api.payload.index.IndexTransactionRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.api.payload.upsert.UpsertTransactionHeaderRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.model.TransactionHeader;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("api/v1/joseph/transaction")
@RestController
@EnableAutoConfiguration
@Component("transactionV1API")
public interface TransactionAPIController {
  @PostMapping("/insert")
  public List<String> insert(@NotNull @RequestBody UpsertTransactionHeaderRequestPayload upsertTransactionHeaderRequestPayload);

  @PostMapping(value = "/", produces = "application/json")
  public @ResponseBody List<TransactionHeader> index(@NotNull @RequestBody IndexTransactionRequestPayload indexTransactionRequestPayload);

  @GetMapping(value = "/show/{id}", produces = "application/json")
  public @ResponseBody TransactionHeader show(@NotNull @PathVariable("id") String id);

  @PutMapping("/update")
  public List<String> update(@NotNull @RequestBody UpsertTransactionHeaderRequestPayload upsertTransactionHeaderRequestPayload);

  @DeleteMapping("/delete/{id}")
  public List<String> delete(@NotNull @PathVariable("id") String id);

  @PostMapping("/report")
  public void generateReport(@NotNull @RequestBody IndexTransactionRequestPayload indexTransactionRequestPayload);

}

package com.joshua.StockManagementSystem.joseph_api.api;

import com.joshua.StockManagementSystem.joseph_api.api.payload.ResponsePayload;
import com.joshua.StockManagementSystem.joseph_api.api.payload.index.IndexProductionRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.api.payload.upsert.UpsertProductionRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.model.Production;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/v1/joseph/production")
@RestController
@EnableAutoConfiguration
@Component("productionV1API")
public interface ProductionAPIController {
  @PostMapping("/insert")
  public ResponsePayload insert(@NotNull @RequestBody UpsertProductionRequestPayload upsertProductionRequestPayload);

  @GetMapping(value = "/", produces = "application/json")
  public @ResponseBody IndexProductionRequestPayload index();

  @GetMapping(value = "/show/{id}", produces = "application/json")
  public @ResponseBody Production show(@NotNull @PathVariable("id") String id);

  @PutMapping("/update")
  public ResponsePayload update(@NotNull @RequestBody UpsertProductionRequestPayload upsertProductionRequestPayload);

  @DeleteMapping("/delete/{id}")
  public ResponsePayload delete(@NotNull @PathVariable("id") String id);
}

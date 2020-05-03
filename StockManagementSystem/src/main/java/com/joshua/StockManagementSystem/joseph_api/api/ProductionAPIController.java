package com.joshua.StockManagementSystem.joseph_api.api;

import com.joshua.StockManagementSystem.joseph_api.api.payload.upsert.UpsertProductionRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.model.Production;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("api/v1/joseph/production")
@RestController
@EnableAutoConfiguration
@Component("productionV1API")
public interface ProductionAPIController {
  @PostMapping("/insert")
  public List<String> insert(@NotNull @RequestBody UpsertProductionRequestPayload upsertProductionRequestPayload);

  @GetMapping(value = "/", produces = "application/json")
  public @ResponseBody List<Production> index();

  @GetMapping(value = "/show/{id}", produces = "application/json")
  public @ResponseBody Production show(@NotNull @PathVariable("id") String id);

  @PutMapping("/update")
  public List<String> update(@NotNull @RequestBody UpsertProductionRequestPayload upsertProductionRequestPayload);

  @DeleteMapping("/delete/{id}")
  public List<String> delete(@NotNull @PathVariable("id") String id);
}

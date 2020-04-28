package com.joshua.StockManagementSystem.joseph_api.api;

import com.joshua.StockManagementSystem.joseph_api.api.payload.UpsertItemRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.model.Item;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("api/v1/joseph/item")
@RestController
@EnableAutoConfiguration
@Component("apiV1")
public interface ItemAPIController {
  @PostMapping("/insert")
  public List<String> insertItem(@NotNull @RequestBody UpsertItemRequestPayload upsertItemRequestPayload);

  @GetMapping(value = "/", produces = "application/json")
  public @ResponseBody List<Item> indexItem();

  @PutMapping("/update")
  public List<String> updateItem(@NotNull @RequestBody UpsertItemRequestPayload upsertItemRequestPayload);

  @DeleteMapping("/delete/{id}")
  public List<String> deleteItem(@NotNull @PathVariable("id") String id);
}


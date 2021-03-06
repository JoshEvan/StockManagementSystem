package com.joshua.StockManagementSystem.joseph_api.api;

import com.joshua.StockManagementSystem.joseph_api.api.payload.ResponsePayload;
import com.joshua.StockManagementSystem.joseph_api.api.payload.index.IndexItemRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.api.payload.index.IndexItemResponsePayload;
import com.joshua.StockManagementSystem.joseph_api.api.payload.upsert.UpsertItemRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.model.Item;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/v1/joseph/item")
@RestController
@EnableAutoConfiguration
@Component("itemAPIV1")
public interface ItemAPIController {
  @PostMapping("/insert")
  public ResponsePayload insertItem(@NotNull @RequestBody UpsertItemRequestPayload upsertItemRequestPayload);

  @PostMapping(value = "/", produces = "application/json")
  public @ResponseBody
  IndexItemResponsePayload indexItem(@NotNull @RequestBody IndexItemRequestPayload indexItemRequestPayload);

  @GetMapping(value = "/show/{id}", produces = "application/json")
  public @ResponseBody
  Item showIndex(@NotNull @PathVariable("id") String id);

  @PutMapping("/update")
  public ResponsePayload updateItem(@NotNull @RequestBody UpsertItemRequestPayload upsertItemRequestPayload);

  @DeleteMapping("/delete/{id}")
  public ResponsePayload deleteItem(@NotNull @PathVariable("id") String id);

  @PostMapping("/report")
  public void generateReport(
          HttpServletResponse response,
          @NotNull @RequestBody IndexItemRequestPayload indexItemRequestPayload) throws IOException;
}


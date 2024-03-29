package com.joshua.StockManagementSystem.joseph_impl.api;

import com.joshua.StockManagementSystem.joseph_api.api.ItemAPIController;
import com.joshua.StockManagementSystem.joseph_api.api.payload.ResponsePayload;
import com.joshua.StockManagementSystem.joseph_api.api.payload.index.IndexItemRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.api.payload.index.IndexItemResponsePayload;
import com.joshua.StockManagementSystem.joseph_api.api.payload.upsert.UpsertItemRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.domain.ItemService;
import com.joshua.StockManagementSystem.joseph_api.model.Item;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.HttpStatus;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.PostgresHelper;
import com.joshua.StockManagementSystem.util.Pair;
import org.flywaydb.core.internal.util.FileCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.*;
import java.net.URL;
import java.util.List;

@Component("itemAPIV1")
public class ItemAPIControllerImpl implements ItemAPIController {
  private final ItemService itemService;

  @Autowired
  public ItemAPIControllerImpl(@Qualifier("itemV1Service") ItemService itemService) {
    this.itemService = itemService;
  }

  @Override
  public ResponsePayload insertItem(@NotNull UpsertItemRequestPayload upsertItemRequestPayload) {
    Pair<Boolean, List<String>> resp = itemService.insert(upsertItemRequestPayload);
    return new ResponsePayload().setMessage(resp.getValue())
            .setStatus((resp.getKey() ? HttpStatus.SUCCESS : HttpStatus.FAIL).toString());
  }

  @Override
  public IndexItemResponsePayload indexItem(IndexItemRequestPayload indexItemRequestPayload) {
    return new IndexItemResponsePayload().setItems(itemService.index(indexItemRequestPayload));
  }

  @Override
  public Item showIndex(@NotNull String id) {
    return itemService.show(id);
  }

  @Override
  public ResponsePayload updateItem(@NotNull UpsertItemRequestPayload upsertItemRequestPayload) {
    Pair<Boolean, List<String>> resp = itemService.update(upsertItemRequestPayload);
    return new ResponsePayload()
            .setMessage(resp.getValue())
            .setStatus(resp.getKey() ? HttpStatus.SUCCESS.toString() : HttpStatus.FAIL.toString());
  }

  @Override
  public ResponsePayload deleteItem(@NotNull String id) {
    Pair<Boolean, List<String>> resp = itemService.delete(id);
    return new ResponsePayload()
            .setMessage(resp.getValue())
            .setStatus(resp.getKey() ? HttpStatus.SUCCESS.toString() : HttpStatus.FAIL.toString());
  }

  @Override
  public void generateReport(
          HttpServletResponse response,
          @NotNull IndexItemRequestPayload indexItemRequestPayload) throws IOException {
    itemService.generateReport(indexItemRequestPayload);
    URL res = getClass().getClassLoader().getResource(PostgresHelper.PDF_PATH+ PostgresHelper.ITEM_PDF_FILENAME);

    File file = new File(System.getProperty("user.dir")+PostgresHelper.PDF_PATH+ PostgresHelper.ITEM_PDF_FILENAME);
    InputStream in = new FileInputStream(file);

    response.setContentType("application/pdf");
    response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
    response.setHeader("Content-Length", String.valueOf(file.length()));
    FileCopyUtils.copy(in, response.getOutputStream());

  }
}

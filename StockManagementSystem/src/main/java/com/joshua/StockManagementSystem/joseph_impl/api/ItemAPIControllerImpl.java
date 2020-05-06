package com.joshua.StockManagementSystem.joseph_impl.api;

import com.joshua.StockManagementSystem.joseph_api.api.ItemAPIController;
import com.joshua.StockManagementSystem.joseph_api.api.payload.index.IndexItemRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.api.payload.index.IndexTransactionRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.api.payload.upsert.UpsertItemRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.domain.ItemService;
import com.joshua.StockManagementSystem.joseph_api.model.Item;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.PostgresHelper;
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
  public List<String> insertItem(@NotNull UpsertItemRequestPayload upsertItemRequestPayload) {
    return itemService.insert(upsertItemRequestPayload);
  }

  @Override
  public List<Item> indexItem(IndexItemRequestPayload indexItemRequestPayload) {
    return itemService.index(indexItemRequestPayload);
  }

  @Override
  public Item showIndex(@NotNull String id) {
    return itemService.show(id);
  }

  @Override
  public List<String> updateItem(@NotNull UpsertItemRequestPayload upsertItemRequestPayload) {
    return itemService.update(upsertItemRequestPayload);
  }

  @Override
  public List<String> deleteItem(@NotNull String id) {
    return itemService.delete(id);
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

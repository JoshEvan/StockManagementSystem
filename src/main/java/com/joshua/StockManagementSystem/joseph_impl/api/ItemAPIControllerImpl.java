package com.joshua.StockManagementSystem.joseph_impl.api;

import com.joshua.StockManagementSystem.joseph_api.api.ItemAPIController;
import com.joshua.StockManagementSystem.joseph_api.api.payload.UpsertItemRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.model.Item;

import javax.validation.constraints.NotNull;
import java.util.LinkedList;
import java.util.List;

public class ItemAPIControllerImpl implements ItemAPIController {

  @Override
  public List<String> insertItem(@NotNull UpsertItemRequestPayload upsertItemRequestPayload) {
    return null;
  }

  @Override
  public List<Item> indexItem() {
    return new LinkedList<>();
  }

  @Override
  public List<String> updateItem(@NotNull UpsertItemRequestPayload upsertItemRequestPayload) {
    return null;
  }

  @Override
  public List<String> deleteItem(@NotNull String idCust) {
    return null;
  }
}

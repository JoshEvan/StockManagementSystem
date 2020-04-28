package com.joshua.StockManagementSystem.joseph_impl.api;

import com.joshua.StockManagementSystem.joseph_api.api.ItemAPIController;
import com.joshua.StockManagementSystem.joseph_api.api.payload.UpsertItemRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.domain.ItemService;
import com.joshua.StockManagementSystem.joseph_api.model.Customer;
import com.joshua.StockManagementSystem.joseph_api.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.LinkedList;
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
  public List<Item> indexItem() {
    return new LinkedList<>();
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
}

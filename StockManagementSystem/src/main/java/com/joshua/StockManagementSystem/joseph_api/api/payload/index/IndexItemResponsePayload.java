package com.joshua.StockManagementSystem.joseph_api.api.payload.index;

import com.joshua.StockManagementSystem.joseph_api.model.Item;

import java.util.List;

public class IndexItemResponsePayload {
  List<Item> items;

  public List<Item> getItems() {
    return items;
  }

  public IndexItemResponsePayload setItems(List<Item> items) {
    this.items = items;return this;
  }
}

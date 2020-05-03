package com.joshua.StockManagementSystem.joseph_impl.infrastructure.adapter;

import com.joshua.StockManagementSystem.joseph_api.api.payload.upsert.UpsertItemRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.model.Item;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout.ItemDataEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class ItemAdapter {
  public static ItemDataEntity convertResultSetToDataEntity(ResultSet resultSet) {
    try {
      return new ItemDataEntity()
              .setItemCode(resultSet.getString(ItemDataEntity.ITEMCODE))
              .setName(resultSet.getString(ItemDataEntity.NAME))
              .setDescription(resultSet.getString(ItemDataEntity.DESCRIPTION))
              .setPrice(resultSet.getFloat(ItemDataEntity.PRICE))
              .setStock(resultSet.getInt(ItemDataEntity.STOCK))
              .setCapacity(resultSet.getInt(ItemDataEntity.CAPACITY))
              ;
    } catch (SQLException throwables) {
      return null;
    }
  }

  public static ItemDataEntity convertUpsertPayloadToDataEntity(UpsertItemRequestPayload upsertItemRequestPayload) {

    return new ItemDataEntity()
            .setItemCode(upsertItemRequestPayload.getItemCode())
            .setName(upsertItemRequestPayload.getName())
            .setDescription(upsertItemRequestPayload.getDescription())
            .setPrice(upsertItemRequestPayload.getPrice())
            .setStock(upsertItemRequestPayload.getStock())
            .setCapacity(upsertItemRequestPayload.getCapacity());
  }

  public static List<Item> convertDataEntitiesToModels(List<ItemDataEntity> dataEntities){
    return dataEntities.stream()
            .map(
                    itemDataEntity -> new Item()
                      .setItemCode(itemDataEntity.getItemCode())
                      .setName(itemDataEntity.getName())
                      .setDescription(itemDataEntity.getDescription())
                      .setPrice(itemDataEntity.getPrice())
                      .setStock(itemDataEntity.getStock())
                      .setCapacity(itemDataEntity.getCapacity())
            ).collect(Collectors.toList());
  }
}

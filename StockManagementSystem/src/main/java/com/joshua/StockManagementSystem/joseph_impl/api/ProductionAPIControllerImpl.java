package com.joshua.StockManagementSystem.joseph_impl.api;

import com.joshua.StockManagementSystem.joseph_api.api.ProductionAPIController;
import com.joshua.StockManagementSystem.joseph_api.api.payload.ResponsePayload;
import com.joshua.StockManagementSystem.joseph_api.api.payload.index.IndexProductionRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.api.payload.upsert.UpsertItemRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.api.payload.upsert.UpsertProductionRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.domain.ItemService;
import com.joshua.StockManagementSystem.joseph_api.domain.ProductionService;
import com.joshua.StockManagementSystem.joseph_api.model.Item;
import com.joshua.StockManagementSystem.joseph_api.model.Production;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.HttpStatus;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.PostgresHelper;
import com.joshua.StockManagementSystem.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Component("productionV1API")
public class ProductionAPIControllerImpl implements ProductionAPIController {
  private final ProductionService productionService;
  private final ItemService itemService;

  @Autowired
  public ProductionAPIControllerImpl(@Qualifier("productionV1Service") ProductionService productionService,
                                     @Qualifier("itemV1Service") ItemService itemService) {
    this.productionService = productionService;
    this.itemService = itemService;
  }

  @Transactional
  @Override
  public ResponsePayload insert(@NotNull UpsertProductionRequestPayload upsertProductionRequestPayload) {
    List<String> messages = new LinkedList<>();
    Pair<Boolean,List<String>> resProd = productionService.insert(upsertProductionRequestPayload);
    messages.addAll(resProd.getValue());
    Item item = itemService.show(upsertProductionRequestPayload.getItemCode());
    if(item == null) {
      messages.add(PostgresHelper.ITEM+PostgresHelper.NOTFOUND);
      return new ResponsePayload()
              .setMessage(messages)
              .setStatus(HttpStatus.FAIL.toString());
    }
    Pair<Boolean,List<String>> resItem = itemService.update(
            new UpsertItemRequestPayload()
            .setItemCode(item.getItemCode())
            .setName(item.getName())
            .setDescription(item.getDescription())
            .setPrice(item.getPriceDec())
            .setCapacity(item.getCapacity())
            .setStock(item.getStock()+upsertProductionRequestPayload.getQuantity())
    );
    messages.addAll(resItem.getValue());
    return new ResponsePayload().setMessage(messages)
            .setStatus(((resProd.getKey() && resItem.getKey()) ? HttpStatus.SUCCESS : HttpStatus.FAIL).toString());
  }

  @Override
  public IndexProductionRequestPayload index() {
    return new IndexProductionRequestPayload().setProductions(productionService.index());
  }

  @Override
  public Production show(@NotNull String id) {
    return productionService.show(id);
  }

  @Override
  public ResponsePayload update(@NotNull UpsertProductionRequestPayload upsertProductionRequestPayload) {
    Production production = productionService.show(upsertProductionRequestPayload.getId());
    if(production == null){
      return new ResponsePayload()
              .setStatus((HttpStatus.FAIL).toString())
              .setMessage(Collections.singletonList(PostgresHelper.PRODUCTION+PostgresHelper.NOTFOUND));
    }
    List<String> messages = new LinkedList<>();
    Item item = itemService.show(production.getItemCode());
    Pair<Boolean, List<String>> ret = productionService.update(upsertProductionRequestPayload);
    messages.addAll(ret.getValue());
    Pair<Boolean, List<String>> resItem = new Pair<>(false,Collections.emptyList());
    if(ret.getKey()) {
      resItem = itemService.update(
        new UpsertItemRequestPayload()
          .setItemCode(item.getItemCode())
          .setName(item.getName())
          .setDescription(item.getDescription())
          .setPrice(item.getPriceDec())
          .setCapacity(item.getCapacity())
          .setStock(item.getStock() - production.getQuantity() + upsertProductionRequestPayload.getQuantity())
      );
      messages.addAll(resItem.getValue());
    }
    return new ResponsePayload()
      .setStatus(((ret.getKey() && resItem.getKey()) ? HttpStatus.SUCCESS : HttpStatus.FAIL).toString())
      .setMessage(messages);
  }

  @Transactional
  @Override
  public ResponsePayload delete(@NotNull String id) {
    Production production = productionService.show(id);
    if(production == null){
      return new ResponsePayload().setStatus((HttpStatus.FAIL).toString()).setMessage(Collections.singletonList(PostgresHelper.PRODUCTION+PostgresHelper.NOTFOUND));
    }
    List<String> messages = new LinkedList<>();
    Item item = itemService.show(production.getItemCode());
    Pair< Boolean, List<String>> ret = productionService.delete(id);
    messages.addAll(ret.getValue());
    Pair<Boolean, List<String>> resItem = new Pair<>(false,Collections.emptyList());
    if(ret.getKey()){
      resItem = itemService.update(
          new UpsertItemRequestPayload()
              .setItemCode(item.getItemCode())
              .setName(item.getName())
              .setDescription(item.getDescription())
              .setPrice(item.getPriceDec())
              .setCapacity(item.getCapacity())
              .setStock(item.getStock()-production.getQuantity())
      );
      messages.addAll(resItem.getValue());
    }
    return new ResponsePayload().setStatus(((ret.getKey() && resItem.getKey()) ? HttpStatus.SUCCESS : HttpStatus.FAIL).toString())
            .setMessage(messages);
  }
}

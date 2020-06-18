package com.joshua.StockManagementSystem.joseph_impl.api;

import com.joshua.StockManagementSystem.joseph_api.api.ProductionAPIController;
import com.joshua.StockManagementSystem.joseph_api.api.payload.ResponsePayload;
import com.joshua.StockManagementSystem.joseph_api.api.payload.index.IndexProductionRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.api.payload.upsert.UpsertProductionRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.domain.ProductionService;
import com.joshua.StockManagementSystem.joseph_api.model.Production;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.HttpStatus;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;

@Component("productionV1API")
public class ProductionAPIControllerImpl implements ProductionAPIController {
  private final ProductionService productionService;

  @Autowired
  public ProductionAPIControllerImpl(@Qualifier("productionV1Service") ProductionService productionService) {
    this.productionService = productionService;
  }

  @Override
  public ResponsePayload insert(@NotNull UpsertProductionRequestPayload upsertProductionRequestPayload) {
    Pair<Boolean,List<String>> res = productionService.insert(upsertProductionRequestPayload);
    return new ResponsePayload().setMessage(res.getValue()).setStatus((res.getKey() ? HttpStatus.SUCCESS : HttpStatus.FAIL).toString());
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
  public List<String> update(@NotNull UpsertProductionRequestPayload upsertProductionRequestPayload) {
    return productionService.update(upsertProductionRequestPayload);
  }

  @Override
  public List<String> delete(@NotNull String id) {
    return productionService.delete(id);
  }
}

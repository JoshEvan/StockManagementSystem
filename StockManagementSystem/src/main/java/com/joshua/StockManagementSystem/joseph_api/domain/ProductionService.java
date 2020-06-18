package com.joshua.StockManagementSystem.joseph_api.domain;


import com.joshua.StockManagementSystem.joseph_api.api.payload.upsert.UpsertProductionRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.model.Production;
import javafx.util.Pair;

import java.util.List;

public interface ProductionService {
  Pair<Boolean,List<String>> insert(UpsertProductionRequestPayload upsertProductionRequestPayload);
  List<Production> index();
  Production show(String id);
  List<String> update(UpsertProductionRequestPayload upsertProductionRequestPayload);
  List<String> delete(String id);
}

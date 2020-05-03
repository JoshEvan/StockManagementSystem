package com.joshua.StockManagementSystem.joseph_impl.domain;

import com.joshua.StockManagementSystem.joseph_api.api.payload.upsert.UpsertProductionRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.domain.ProductionService;
import com.joshua.StockManagementSystem.joseph_api.infrastructure.dao.ProductionDAO;
import com.joshua.StockManagementSystem.joseph_api.model.Production;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.PostgresHelper;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout.ProductionDataEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static com.joshua.StockManagementSystem.joseph_impl.infrastructure.PostgresHelper.*;
import static com.joshua.StockManagementSystem.joseph_impl.infrastructure.adapter.ProductionAdapter.*;

@Component("productionV1Service")
@Service
public class ProductionServiceImpl implements ProductionService {
  private final ProductionDAO productionDAO;

  @Autowired
  public ProductionServiceImpl(@Qualifier("postgresProduction") ProductionDAO productionDAO) {
    this.productionDAO = productionDAO;
  }

  @Override
  public List<String> insert(UpsertProductionRequestPayload upsertProductionRequestPayload) {
    List<String> stats = new LinkedList<>();
    if(productionDAO.insert(convertUpsertPayloadToDataEntity(upsertProductionRequestPayload)) == 1){
      stats.add(ITEM+ upsertProductionRequestPayload.getId()+ SUCCESS+" "+ PostgresHelper.INSERTED);
    }else{
      stats.add(ITEM+ upsertProductionRequestPayload.getId()+ FAIL+" "+ PostgresHelper.INSERTED);
    }
    return stats;
  }

  @Override
  public List<Production> index() {
    return convertDataEntitiesToModels(productionDAO.index());
  }

  @Override
  public Production show(String id) {
    ProductionDataEntity dataEntity = productionDAO.show(id).orElse(null);
    return (dataEntity == null) ? null :
            convertDataEntitiesToModels(Collections.singletonList(dataEntity)).get(0);

  }

  @Override
  public List<String> update(UpsertProductionRequestPayload upsertProductionRequestPayload) {
    List<String> stats = new LinkedList<>();
    if(productionDAO.update(convertUpsertPayloadToDataEntity(upsertProductionRequestPayload)) == 1){
      stats.add(ITEM+ upsertProductionRequestPayload.getId()+ SUCCESS+" "+ PostgresHelper.UPDATED);
    }else{
      stats.add(ITEM+ upsertProductionRequestPayload.getId()+ FAIL+" "+ PostgresHelper.UPDATED);
    }
    return stats;
  }

  @Override
  public List<String> delete(String id) {
    List<String> stats = new LinkedList<>();
    if(productionDAO.delete(id) == 1){
      stats.add( ITEM+ id + SUCCESS+" "+ PostgresHelper.REMOVED);
    }else{
      stats.add(ITEM+ id + FAIL+" "+ PostgresHelper.REMOVED);
    }
    return stats;
  }
}

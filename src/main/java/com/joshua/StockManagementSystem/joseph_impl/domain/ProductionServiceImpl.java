package com.joshua.StockManagementSystem.joseph_impl.domain;

import com.joshua.StockManagementSystem.joseph_api.api.payload.upsert.UpsertProductionRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.domain.ProductionService;
import com.joshua.StockManagementSystem.joseph_api.infrastructure.dao.ProductionDAO;
import com.joshua.StockManagementSystem.joseph_api.model.Production;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.PostgresHelper;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout.ProductionDataEntity;
import javafx.util.Pair;
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
  public Pair<Boolean,List<String>> insert(UpsertProductionRequestPayload upsertProductionRequestPayload) {
    Integer flag = productionDAO.insert(convertUpsertPayloadToDataEntity(upsertProductionRequestPayload));
    return new Pair<>(flag == 1, Collections.singletonList(
            PRODUCTION+ upsertProductionRequestPayload.getId()+ (flag == 1? SUCCESS : FAIL )+" "+ PostgresHelper.INSERTED));
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
  public Pair<Boolean,List<String>> update(UpsertProductionRequestPayload upsertProductionRequestPayload) {
    List<String> stats = new LinkedList<>();
    Integer flag =productionDAO.update(convertUpsertPayloadToDataEntity(upsertProductionRequestPayload));
    return new Pair<>(
      (flag == 1 ),
      Collections.singletonList(PRODUCTION+ upsertProductionRequestPayload.getId()+(flag == 1 ? SUCCESS : FAIL)+UPDATED)
    );
  }

  @Override
  public Pair<Boolean,List<String> >delete(String id) {
    Integer flag = productionDAO.delete(id);
    return new Pair<>((flag == 1), Collections.singletonList(
            PRODUCTION+ id + ((flag == 1)? SUCCESS : FAIL )+PostgresHelper.REMOVED));

  }
}

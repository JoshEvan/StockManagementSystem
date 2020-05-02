package com.joshua.StockManagementSystem.joseph_impl.infrastructure.adapter;



import com.joshua.StockManagementSystem.joseph_api.api.payload.UpsertProductionRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.model.Production;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout.ProductionDataEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.security.krb5.internal.PAData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class ProductionAdapter {
  private final static Logger log = LoggerFactory.getLogger(ProductionAdapter.class);
  public static ProductionDataEntity convertResultSetToDataEntity(ResultSet resultSet) {
    try {
      return new ProductionDataEntity()
              .setId(resultSet.getString(ProductionDataEntity.ID))
              .setItemCode(resultSet.getString(ProductionDataEntity.ITEMCODE))
              .setProducer(resultSet.getString(ProductionDataEntity.PRODUCER))
              .setProductionDate(resultSet.getDate(ProductionDataEntity.PRODDATE))
              .setQuantity(resultSet.getInt(ProductionDataEntity.QTY));
    } catch (SQLException throwables) {
      log.error("error covertResultSet ",throwables);
      return null;
    }
  }

  public static ProductionDataEntity convertUpsertPayloadToDataEntity(UpsertProductionRequestPayload upsertProductionRequestPayload) {

    return new ProductionDataEntity()
            .setId(upsertProductionRequestPayload.getId())
            .setItemCode(upsertProductionRequestPayload.getItemCode())
            .setProducer(upsertProductionRequestPayload.getProducer())
            .setProductionDate(upsertProductionRequestPayload.getProductionDate())
            .setQuantity(upsertProductionRequestPayload.getQuantity());
  }

  public static List<Production> convertDataEntitiesToModels(List<ProductionDataEntity> dataEntities){
    return dataEntities.stream()
            .map(
                    productionDataEntity -> new Production()
                            .setId(productionDataEntity.getId())
                            .setItemCode(productionDataEntity.getItemCode())
                            .setProducer(productionDataEntity.getProducer())
                            .setProductionDate(productionDataEntity.getProductionDate())
                            .setQuantity(productionDataEntity.getQuantity())
            ).collect(Collectors.toList());
  }
}

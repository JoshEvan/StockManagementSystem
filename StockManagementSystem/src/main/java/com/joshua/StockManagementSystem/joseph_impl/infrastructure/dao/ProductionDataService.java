package com.joshua.StockManagementSystem.joseph_impl.infrastructure.dao;

import com.joshua.StockManagementSystem.joseph_api.infrastructure.dao.ProductionDAO;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.PostgresHelper;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout.ItemDataEntity;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout.ProductionDataEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static com.joshua.StockManagementSystem.joseph_impl.infrastructure.adapter.ProductionAdapter.*;

@Repository("postgresProduction")
public class ProductionDataService implements ProductionDAO {
  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public ProductionDataService(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public Integer insert(ProductionDataEntity productionDataEntity) {
    if(show(productionDataEntity.getId()) != null){
      return update(productionDataEntity);
    }
    final String sql = PostgresHelper.insertOperation(productionDataEntity);
    return jdbcTemplate.update(sql
            ,productionDataEntity.getId()
            ,productionDataEntity.getItemCode()
            ,productionDataEntity.getProductionDate()
            ,productionDataEntity.getProducer()
            ,productionDataEntity.getQuantity()
    );
  }

  @Override
  public List<ProductionDataEntity> index() {
    final String sql = PostgresHelper.selectOperation(new ProductionDataEntity())
            + " WHERE "+ItemDataEntity.ISACTIVE+" = true";
    return jdbcTemplate.query(
            sql, ((resultSet, i) -> {
              return convertResultSetToDataEntity(resultSet);
            })
    );
  }

  @Override
  public Optional<ProductionDataEntity> show(String id) {
    final String sql = PostgresHelper.selectOperation(new ProductionDataEntity())
            + " WHERE "+ProductionDataEntity.ID +" = ?";

    try{
      ProductionDataEntity productionDataEntity =  jdbcTemplate.queryForObject(sql, new Object[]{id}, ((resultSet, i) -> {
        return convertResultSetToDataEntity(resultSet);
      }));
      return Optional.ofNullable(productionDataEntity);
    }catch (Exception e){
      return null;
    }
  }

  @Override
  public Integer update(ProductionDataEntity productionDataEntity) {
    HashMap<String,Object> setter = new HashMap<>();
    setter.put(ProductionDataEntity.ID, productionDataEntity.getId());
    setter.put(ProductionDataEntity.ITEMCODE, productionDataEntity.getItemCode());
    setter.put(ProductionDataEntity.PRODDATE, productionDataEntity.getProductionDate());
    setter.put(ProductionDataEntity.PRODUCER, productionDataEntity.getProducer());
    setter.put(ProductionDataEntity.QTY, productionDataEntity.getQuantity());
    setter.put(ItemDataEntity.ISACTIVE, true);

    final String sql = PostgresHelper.updateOperation(productionDataEntity,
            setter,ProductionDataEntity.ID+" = \'" +productionDataEntity.getId()+"\'");
    return jdbcTemplate.update(sql);
  }

  @Override
  public Integer delete(String id) {
    ProductionDataEntity productionDataEntity = show(id).orElse(null);
    if(productionDataEntity == null) return 0;
    HashMap<String,Object> setter = new HashMap<>();
    setter.put(ItemDataEntity.ISACTIVE, false);
    final String sql = PostgresHelper.updateOperation(productionDataEntity,
            setter,ItemDataEntity.ITEMCODE+" = \'" +productionDataEntity.getItemCode()+"\'");
    return jdbcTemplate.update(sql);
  }
}

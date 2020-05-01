package com.joshua.StockManagementSystem.joseph_impl.infrastructure.dao;

import com.joshua.StockManagementSystem.joseph_api.infrastructure.dao.ItemDAO;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.PostgresHelper;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout.ItemDataEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import static com.joshua.StockManagementSystem.joseph_impl.infrastructure.adapter.ItemAdapter.convertResultSetToDataEntity;

@Repository("postgresItem")
public class ItemDataAccessService implements ItemDAO {
  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public ItemDataAccessService(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public Integer insert(ItemDataEntity itemDataEntity) {
    final String sql = PostgresHelper.insertOperation(itemDataEntity);
    return jdbcTemplate.update(sql
            ,itemDataEntity.getItemCode()
            ,itemDataEntity.getName()
            ,itemDataEntity.getDescription()
            ,itemDataEntity.getPrice()
            ,itemDataEntity.getStock()
            ,itemDataEntity.getCapacity()
    );
  }

  @Override
  public List<ItemDataEntity> index() {
    final String sql = PostgresHelper.selectOperation(new ItemDataEntity());
    return jdbcTemplate.query(
            sql, ((resultSet, i) -> {
              ItemDataEntity dataEntity = convertResultSetToDataEntity(resultSet);
              return dataEntity;
            })
    );
  }

  @Override
  public Optional<ItemDataEntity> show(String id) {
    final String sql = PostgresHelper.selectOperation(new ItemDataEntity())
            + " WHERE "+ItemDataEntity.ITEMCODE +" = ?";
    try {
      ItemDataEntity itemDataEntity = jdbcTemplate.queryForObject(sql, new Object[]{id},
              ((resultSet, i) -> {
                return convertResultSetToDataEntity(resultSet);
              }));
      return Optional.ofNullable(itemDataEntity);
    }catch(Exception e){
      return null;
    }
  }

  @Override
  public Integer update(ItemDataEntity itemDataEntity) {
    HashMap<String,Object> setter = new HashMap<>();
    setter.put(ItemDataEntity.NAME, itemDataEntity.getName());
    setter.put(ItemDataEntity.DESCRIPTION, itemDataEntity.getDescription());
    setter.put(ItemDataEntity.PRICE, itemDataEntity.getPrice());
    setter.put(ItemDataEntity.STOCK, itemDataEntity.getStock());
    setter.put(ItemDataEntity.CAPACITY, itemDataEntity.getCapacity());
    final String sql = PostgresHelper.updateOperation(itemDataEntity,
            setter,ItemDataEntity.ITEMCODE+" = \'" +itemDataEntity.getItemCode()+"\'");
    return jdbcTemplate.update(sql);
  }

  @Override
  public Integer delete(String idItem) {
    final  String sql = PostgresHelper.deleteOperation(new ItemDataEntity(),"WHERE "+ItemDataEntity.ITEMCODE+" = \'"+idItem+'\'');
    return jdbcTemplate.update(sql);
  }
}

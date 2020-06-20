package com.joshua.StockManagementSystem.joseph_impl.infrastructure.dao;

import com.joshua.StockManagementSystem.joseph_api.infrastructure.dao.PaymentDAO;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.PostgresHelper;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout.ItemDataEntity;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout.PaymentDataEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static com.joshua.StockManagementSystem.joseph_impl.infrastructure.adapter.PaymentAdapter.convertResultSetToDataEntity;

@Repository("postgresPayment")
public class PaymentDataAccessService implements PaymentDAO {
  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public PaymentDataAccessService(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public Integer insert(PaymentDataEntity payment) {
    if(show(payment.getId()) != null){
      return update(payment);
    }
    final String sql = PostgresHelper.insertOperation(payment);
    return jdbcTemplate.update(sql
            ,payment.getId()
            ,payment.getPayment_type()
    );
  }

  @Override
  public List<PaymentDataEntity> index() {
    final String sql = PostgresHelper.selectOperation(new PaymentDataEntity())
            + " WHERE "+ItemDataEntity.ISACTIVE+" = true";
    return jdbcTemplate.query(
            sql, ((resultSet, i) -> {
              PaymentDataEntity dataEntity = convertResultSetToDataEntity(resultSet);
              return dataEntity;
            })
    );
  }

  @Override
  public Optional<PaymentDataEntity> show(String id) {
    final String sql = PostgresHelper.selectOperation(new PaymentDataEntity())
            + " WHERE "+PaymentDataEntity.ID +" = ?";

    try{
      PaymentDataEntity paymentDataEntity =  jdbcTemplate.queryForObject(sql, new Object[]{id}, ((resultSet, i) -> {
        return convertResultSetToDataEntity(resultSet);
      }));
      return Optional.ofNullable(paymentDataEntity);
    }catch (Exception e){
      return null;
    }
  }

  @Override
  public Integer update(PaymentDataEntity paymentDataEntity) {
    HashMap<String,Object> setter = new HashMap<>();
    setter.put(PaymentDataEntity.ID, paymentDataEntity.getId());
    setter.put(PaymentDataEntity.TYPE, paymentDataEntity.getPayment_type());
    setter.put(ItemDataEntity.ISACTIVE, true);
    final String sql = PostgresHelper.updateOperation(paymentDataEntity,
            setter,PaymentDataEntity.ID+" = \'" +paymentDataEntity.getId()+"\'");
    return jdbcTemplate.update(sql);
  }

  @Override
  public Integer delete(String id) {
    PaymentDataEntity paymentDataEntity = show(id).orElse(null);
    if(paymentDataEntity == null) return 0;
    HashMap<String,Object> setter = new HashMap<>();
    setter.put(ItemDataEntity.ISACTIVE, false);
    final String sql = PostgresHelper.updateOperation(paymentDataEntity,
            setter,PaymentDataEntity.ID+" = \'" +paymentDataEntity.getId()+"\'");
    return jdbcTemplate.update(sql);
  }
}

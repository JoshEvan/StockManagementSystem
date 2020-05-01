package com.joshua.StockManagementSystem.joseph_impl.infrastructure.dao;

import com.joshua.StockManagementSystem.joseph_api.infrastructure.dao.TransactionDAO;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.PostgresHelper;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.dao.spec.TransactionSpec;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout.ProductionDataEntity;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout.TransactionDetailDataEntity;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout.TransactionHeaderDataEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.joshua.StockManagementSystem.joseph_impl.infrastructure.adapter.TransactionAdapter.convertResultSetToDataEntity;

@Repository("postgresTransaction")
public class TransactionDataAccessService implements TransactionDAO {
  private final JdbcTemplate jdbcTemplate;
  private final static Logger log = LoggerFactory.getLogger(TransactionDataAccessService.class);


  @Autowired
  public TransactionDataAccessService(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  @Transactional
  public Integer insert(TransactionHeaderDataEntity transactionHeaderDataEntity,
                        List<TransactionDetailDataEntity> details) {
    final String sql = PostgresHelper.insertOperation(transactionHeaderDataEntity);
    int stats = jdbcTemplate.update(sql
            ,transactionHeaderDataEntity.getId()
            ,transactionHeaderDataEntity.getCustomerId()
            ,transactionHeaderDataEntity.getPaymentId()
            ,transactionHeaderDataEntity.getTransactionDate()
            ,transactionHeaderDataEntity.getPaymentStatus()
            ,transactionHeaderDataEntity.getNote()
    );
    log.info(String.valueOf(stats));
    log.info("success insert trans detail "+ transactionHeaderDataEntity.getId());
    for(TransactionDetailDataEntity detail: details){
      final String sqlDet = PostgresHelper.insertOperation(detail);
      stats = jdbcTemplate.update(sqlDet
            ,transactionHeaderDataEntity.getId()
            ,detail.getItemCode()
            ,detail.getPrice()
            ,detail.getQuantity()
            ,detail.getNote()
      );
      log.info(String.valueOf(stats));
      log.info("success insert trans detail" + detail.getItemCode());

    }
    log.info(String.valueOf(stats));
    return stats;
  }

  @Override
  public List<TransactionSpec> index() {
    List<TransactionSpec> results = new LinkedList<>();
    final String sql = PostgresHelper.selectOperation(new TransactionHeaderDataEntity());
    List<TransactionHeaderDataEntity> transactionHeaderDataEntities = jdbcTemplate.query(
            sql, ((resultSet, i) -> {
              return convertResultSetToDataEntity(resultSet);
            })
    );

    transactionHeaderDataEntities
      .forEach(
        transactionHeaderDataEntity -> {
          final String sqlDet =
                  PostgresHelper.selectOperation(new TransactionDetailDataEntity())
                  +" WHERE "
                  +TransactionDetailDataEntity.TRANSHID+" = \'"+transactionHeaderDataEntity.getId()+"\'";
          List<TransactionDetailDataEntity> transactionDetailDataEntities =
            jdbcTemplate.query(
                    sqlDet, ((resultSet, i) ->
                new  TransactionDetailDataEntity()
                  .setItemCode(resultSet.getString(TransactionDetailDataEntity.ITEMCODE))
                  .setQuantity(resultSet.getInt(TransactionDetailDataEntity.QTY))
                  .setTransactionHeaderId(resultSet.getString(TransactionDetailDataEntity.TRANSHID))
                  .setPrice(resultSet.getFloat(TransactionDetailDataEntity.PRICE))
                  .setNote(resultSet.getString(TransactionDetailDataEntity.NOTE))
              )
            );
            results.add(new TransactionSpec()
              .setTransactionHeaderDataEntity(transactionHeaderDataEntity)
              .setTransactionDetailDataEntityList(transactionDetailDataEntities)
            );
        }
      );
    return  results;
  }

  @Override
  public Optional<ProductionDataEntity> show(String id) {
    return Optional.empty();
  }

  @Override
  public Integer update(ProductionDataEntity productionDataEntity) {
    return null;
  }

  @Override
  public Integer delete(String idItem) {
    return null;
  }
}

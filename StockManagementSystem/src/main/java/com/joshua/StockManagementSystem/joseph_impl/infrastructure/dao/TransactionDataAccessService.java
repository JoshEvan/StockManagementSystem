package com.joshua.StockManagementSystem.joseph_impl.infrastructure.dao;

import com.joshua.StockManagementSystem.joseph_api.infrastructure.dao.TransactionDAO;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.PostgresHelper;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.dao.spec.TransactionSpec;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout.ItemDataEntity;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout.ProductionDataEntity;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout.TransactionDetailDataEntity;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout.TransactionHeaderDataEntity;
import org.omg.PortableInterceptor.SUCCESSFUL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static com.joshua.StockManagementSystem.joseph_impl.infrastructure.adapter.TransactionAdapter.convertDetailResultSetToDataEntity;
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
    final String sql = PostgresHelper.selectOperation(new TransactionHeaderDataEntity())
            + " WHERE "+ ItemDataEntity.ISACTIVE+" = true";
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
  public Optional<TransactionSpec> show(String id) {
    final String sql = PostgresHelper.selectOperation(new TransactionHeaderDataEntity())
            +" WHERE "+TransactionHeaderDataEntity.ID+" = \'"+id+"\'";

    final String sqlDet = PostgresHelper.selectOperation(new TransactionDetailDataEntity())
            +" WHERE "+TransactionDetailDataEntity.TRANSHID+" = \'"+id+"\'";

    TransactionSpec transactionSpec = new TransactionSpec()
      .setTransactionHeaderDataEntity(
      jdbcTemplate.queryForObject(sql, (((resultSet, i) ->
            convertResultSetToDataEntity(resultSet)
        )))
      )
      .setTransactionDetailDataEntityList(
        jdbcTemplate.query(
          sqlDet,
          (((resultSet, i) ->
            convertDetailResultSetToDataEntity(resultSet)
          ))
        )
      );
    return Optional.ofNullable(transactionSpec);
  }

  @Override
  @Transactional
  public Integer update(TransactionHeaderDataEntity transactionHeaderDataEntity, List<TransactionDetailDataEntity> details) {
    Integer stats = delete(transactionHeaderDataEntity.getId());
    if(stats == 0) return stats;
    stats = insert(transactionHeaderDataEntity, details);
    return stats;
  }

  @Override
  @Transactional
  public Integer delete(String id){
    TransactionSpec transactionSpec = show(id).orElse(null);
    if(transactionSpec == null){
      return 0;
    }
//    for(TransactionDetailDataEntity detailDataEntity: transactionSpec.getTransactionDetailDataEntityList()){
//      log.info("applying delete detail transaction "+ detailDataEntity.getItemCode());
//      final  String sqlDet =
//      PostgresHelper.deleteOperation(new TransactionDetailDataEntity(),"WHERE "+TransactionDetailDataEntity.TRANSHID+" = \'"+id+'\'');
//      jdbcTemplate.update(sqlDet);
//      log.info(PostgresHelper.SUCCESS +"delete detail transaction "+ detailDataEntity.getItemCode());
//    }
    log.info("applying delete header transaction "+id);
    HashMap<String,Object> setter = new HashMap<>();
    setter.put(TransactionHeaderDataEntity.ISACTIVE,false);
    final String sql = PostgresHelper.updateOperation(transactionSpec.getTransactionHeaderDataEntity(),
            setter,TransactionHeaderDataEntity.ID+" = \'" +transactionSpec.getTransactionHeaderDataEntity().getId()+"\'");
    Integer stats =  jdbcTemplate.update(sql);
    log.info((stats == 1) ? "success apply delete header transaction "+id : "fail apply delete header transaction "+id); ;
    return stats;
  }
}

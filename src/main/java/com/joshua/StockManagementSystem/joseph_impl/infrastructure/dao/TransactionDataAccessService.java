package com.joshua.StockManagementSystem.joseph_impl.infrastructure.dao;

import com.joshua.StockManagementSystem.joseph_api.api.payload.index.IndexTransactionRequestPayload;
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

import java.util.*;

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

  private String applyFilter(IndexTransactionRequestPayload indexTransactionRequestPayload){
    String condition = "";
    if(!indexTransactionRequestPayload.getCustomerFilter().isEmpty()){
      condition+=" AND (";
      int sz = indexTransactionRequestPayload.getCustomerFilter().size();
      for(int i = 0;i<sz;i++){
        if(i > 0) condition+=" OR ";
        String id  = indexTransactionRequestPayload.getCustomerFilter().get(i);
        condition+=TransactionHeaderDataEntity.CUSTID+" = \'"+id+"\'";
      }
      condition+=")";
    }

    if(!indexTransactionRequestPayload.getPaymentFilter().isEmpty()){
      condition+=" AND (";
      int sz = indexTransactionRequestPayload.getPaymentFilter().size();
      for(int i = 0;i<sz;i++){
        if(i > 0) condition+=" OR ";
        String id  = indexTransactionRequestPayload.getPaymentFilter().get(i);
        condition+=TransactionHeaderDataEntity.PAYID+" = \'"+id+"\'";
      }
      condition+=")";
    }

    if(!indexTransactionRequestPayload.getTransactionIdFilter().isEmpty()){
      condition+=" AND (";
      int sz = indexTransactionRequestPayload.getTransactionIdFilter().size();
      for(int i = 0;i<sz;i++){
        if(i > 0) condition+=" OR ";
        String id  = indexTransactionRequestPayload.getTransactionIdFilter().get(i);
        condition+=TransactionHeaderDataEntity.ID+" = \'"+id+"\'";
      }
      condition+=")";
    }

    if(indexTransactionRequestPayload.getDateFilter() != "" && indexTransactionRequestPayload.getEndDateFilter() != ""){
      condition+=" AND ("+TransactionHeaderDataEntity.TRANDATE
              +" >= "+ indexTransactionRequestPayload.getDateFilter()
              +" AND "+
              TransactionHeaderDataEntity.TRANDATE
              +" <= "+indexTransactionRequestPayload.getEndDateFilter()
              +")";
    }else if(indexTransactionRequestPayload.getDateFilter()!= "" && indexTransactionRequestPayload.getEndDateFilter() == ""){
      condition+=" AND ("+TransactionHeaderDataEntity.TRANDATE
              +" = "+ indexTransactionRequestPayload.getDateFilter()
              +")";
    }

    return condition;
  }

  private String applySort(IndexTransactionRequestPayload indexTransactionRequestPayload ){
    String condition = "";
    if(indexTransactionRequestPayload.getSortByDate() > 0){
      // ASC
      condition+=" ORDER BY "+TransactionHeaderDataEntity.TRANDATE+" ASC";
    }else if(indexTransactionRequestPayload.getSortByDate() < 0){
      // DESC
      condition+=" ORDER BY "+TransactionHeaderDataEntity.TRANDATE+" DESC";
    }
    return condition;
  }


  private String applyDetailFilter(
          IndexTransactionRequestPayload indexTransactionRequestPayload,
          HashMap<String,Integer> indexOfId){
    String condition = "";

    if(!indexTransactionRequestPayload.getItemFilter().isEmpty()){
      if(!condition.equals("")) condition+=" AND (";
      else condition+= " (";

      int sz = indexTransactionRequestPayload.getItemFilter().size();
      for(int i = 0;i<sz;i++){
        if(i > 0) condition+=" OR ";
        String id  = indexTransactionRequestPayload.getItemFilter().get(i);
        condition+=TransactionDetailDataEntity.ITEMCODE+" = \'"+id+"\'";
      }
      condition+=")";
    }

    if(!indexOfId.isEmpty()){
      if(!condition.equals("")) condition+=" AND (";
      else condition+= " (";

      List<String> ids = new LinkedList<>(indexOfId.keySet());
      int sz = ids.size();
      for(int i = 0;i<sz;i++){
        if(i > 0) condition+=" OR ";
        String id  = ids.get(i);
        condition+=TransactionDetailDataEntity.TRANSHID+" = \'"+id+"\'";
      }
      condition+=")";
    }

    return (condition.equals("")) ? condition : " WHERE "+condition;
  }

  @Override
  public List<TransactionSpec> index(IndexTransactionRequestPayload indexTransactionRequestPayload) {
    List<TransactionSpec> results = new LinkedList<>();
    HashMap<String, Integer> indexOfId = new HashMap<>();

    final String sql = PostgresHelper.selectOperation(new TransactionHeaderDataEntity())
            + " WHERE "+ ItemDataEntity.ISACTIVE+" = true"
            +applyFilter(indexTransactionRequestPayload)
            +applySort(indexTransactionRequestPayload);

    List<TransactionHeaderDataEntity> transactionHeaderDataEntities = jdbcTemplate.query(
            sql, ((resultSet, i) -> {
              return convertResultSetToDataEntity(resultSet);
            })
    );

    int sz = transactionHeaderDataEntities.size();
    for(int i = 0;i<sz;i++){
      indexOfId.put(transactionHeaderDataEntities.get(i).getId(),i);
      results.add(new TransactionSpec()
              .setTransactionHeaderDataEntity(transactionHeaderDataEntities.get(i))
              .setTransactionDetailDataEntityList(new LinkedList<>())
      );
    }

  final String sqlDet =
          PostgresHelper.selectOperation(new TransactionDetailDataEntity())
          +applyDetailFilter(indexTransactionRequestPayload, indexOfId);

  List<TransactionDetailDataEntity> transactionDetailDataEntities =
    jdbcTemplate.query(
            sqlDet, ((resultSet, i) ->
        new  TransactionDetailDataEntity()
          .setItemCode(resultSet.getString(TransactionDetailDataEntity.ITEMCODE))
          .setQuantity(resultSet.getInt(TransactionDetailDataEntity.QTY))
          .setTransactionHeaderId(resultSet.getString(TransactionDetailDataEntity.TRANSHID))
          .setPrice(resultSet.getBigDecimal(TransactionDetailDataEntity.PRICE))
          .setNote(resultSet.getString(TransactionDetailDataEntity.NOTE))
          .setTimestamp(resultSet.getTimestamp(TransactionDetailDataEntity.TIMESTAMP))
      )
    );

    // mapping
    sz = transactionDetailDataEntities.size();

    for(int i = 0;i<sz;i++){
      if(!indexOfId.containsKey(transactionDetailDataEntities.get(i).getTransactionHeaderId())) continue;
      results.get(indexOfId.get(transactionDetailDataEntities.get(i).getTransactionHeaderId()))
              .getTransactionDetailDataEntityList().add(transactionDetailDataEntities.get(i));
    }

    sz = results.size();
    for(int i = 0;i<sz;i++){
     if(results.get(i).getTransactionDetailDataEntityList().isEmpty()){
        results.remove(i);i--;sz = results.size();
     }
    }

    return  results;
  }
//  filter: by product(multiple) , by customer (multiple), payment type (multiple) , by date range
//  sort: by total income, date

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
    // USING HARD DELETE FOR TRANSACTION ONLY

    TransactionSpec transactionSpec = show(id).orElse(null);
    if(transactionSpec == null){
      return 0;
    }
    for(TransactionDetailDataEntity detailDataEntity: transactionSpec.getTransactionDetailDataEntityList()){
      log.info("applying delete detail transaction "+ detailDataEntity.getItemCode());
      final  String sqlDet =
      PostgresHelper.deleteOperation(new TransactionDetailDataEntity(),"WHERE "+TransactionDetailDataEntity.TRANSHID+" = \'"+id+'\'');
      jdbcTemplate.update(sqlDet);
      log.info(PostgresHelper.SUCCESS +"delete detail transaction "+ detailDataEntity.getItemCode());
    }
    log.info("applying delete header transaction "+id);

    final String sql = PostgresHelper.deleteOperation(new TransactionHeaderDataEntity(),
            "WHERE "+TransactionHeaderDataEntity.ID+" = \'"+id+"\'");
    Integer stats =  jdbcTemplate.update(sql);

    log.info((stats == 1) ? "success apply delete header transaction "+id : "fail apply delete header transaction "+id); ;
    return stats;
  }
}

package com.joshua.StockManagementSystem.joseph_impl.infrastructure.adapter;

import com.joshua.StockManagementSystem.joseph_api.api.payload.upsert.UpsertTransactionHeaderRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.model.TransactionDetail;
import com.joshua.StockManagementSystem.joseph_api.model.TransactionHeader;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.dao.spec.TransactionSpec;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout.TransactionDetailDataEntity;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout.TransactionHeaderDataEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionAdapter {
  public static TransactionHeaderDataEntity convertResultSetToDataEntity(ResultSet resultSet) {
    try {
      return new TransactionHeaderDataEntity()
              .setId(resultSet.getString(TransactionHeaderDataEntity.ID))
              .setPaymentId(resultSet.getString(TransactionHeaderDataEntity.PAYID))
              .setCustomerId(resultSet.getString(TransactionHeaderDataEntity.CUSTID))
              .setPaymentStatus(resultSet.getString(TransactionHeaderDataEntity.PAYSTAT))
              .setTransactionDate(resultSet.getDate(TransactionHeaderDataEntity.TRANDATE))
              .setNote(resultSet.getString(TransactionHeaderDataEntity.NOTE))
              .setTimestamp(resultSet.getTimestamp(TransactionHeaderDataEntity.TIMESTAMP));
    } catch (SQLException throwables) {
      return new TransactionHeaderDataEntity();
    }
  }

  public static TransactionSpec convertUpsertPayloadToDataEntity(
            UpsertTransactionHeaderRequestPayload upsertTransactionHeaderRequestPayload) {

    return new TransactionSpec()
              .setTransactionHeaderDataEntity(
                      new TransactionHeaderDataEntity()
                        .setId(upsertTransactionHeaderRequestPayload.getId())
                        .setTransactionDate(upsertTransactionHeaderRequestPayload.getTransactionDate())
                        .setCustomerId(upsertTransactionHeaderRequestPayload.getCustomerId())
                        .setPaymentId(upsertTransactionHeaderRequestPayload.getPaymentId())
                        .setPaymentStatus(upsertTransactionHeaderRequestPayload.getPaymentStatus())
                        .setPaymentId(upsertTransactionHeaderRequestPayload.getPaymentId())
                        .setNote(upsertTransactionHeaderRequestPayload.getNote())
              )
              .setTransactionDetailDataEntityList(
                      convertUpsertDetailPayloadToDataEntity(upsertTransactionHeaderRequestPayload)
              );
  }

  public static List<TransactionDetailDataEntity>
  convertUpsertDetailPayloadToDataEntity(UpsertTransactionHeaderRequestPayload upsertTransactionHeaderRequestPayload){
     return upsertTransactionHeaderRequestPayload.getTransactionDetails().stream()
             .map(
                 upsertTransactionDetailRequestPayload
                   -> new TransactionDetailDataEntity()
                     .setTransactionHeaderId(upsertTransactionHeaderRequestPayload.getId())
                     .setItemCode(upsertTransactionDetailRequestPayload.getItemCode())
                     .setQuantity(upsertTransactionDetailRequestPayload.getQuantity())
                     .setNote(upsertTransactionDetailRequestPayload.getNote())
             ).collect(Collectors.toList());
  }

  public static TransactionDetailDataEntity convertDetailResultSetToDataEntity(ResultSet resultSet){
    try {
      return new TransactionDetailDataEntity()
              .setTransactionHeaderId(resultSet.getString(TransactionDetailDataEntity.TRANSHID))
              .setPrice(resultSet.getBigDecimal(TransactionDetailDataEntity.PRICE))
              .setNote(resultSet.getString(TransactionDetailDataEntity.NOTE))
              .setQuantity(resultSet.getInt(TransactionDetailDataEntity.QTY))
              .setItemCode(resultSet.getString(TransactionDetailDataEntity.ITEMCODE))
              .setTimestamp(resultSet.getTimestamp(TransactionDetailDataEntity.TIMESTAMP));
    }catch (Exception e){
      return new TransactionDetailDataEntity();
    }
  }


  public static List<TransactionHeader> convertTransactionSpecsToModels(List<TransactionSpec> transactionSpecs){
    return transactionSpecs.stream()
      .map(
        transactionSpec ->
          convertTransactionSpecToModel(transactionSpec)
      ).collect(Collectors.toList());
  }

  public static TransactionHeader convertTransactionSpecToModel(TransactionSpec transactionSpec){
    return new TransactionHeader()
            .setId(transactionSpec.getTransactionHeaderDataEntity().getId())
            .setPaymentStatus(transactionSpec.getTransactionHeaderDataEntity().getPaymentStatus())
            .setPaymentId(transactionSpec.getTransactionHeaderDataEntity().getPaymentId())
            .setCustomerId(transactionSpec.getTransactionHeaderDataEntity().getCustomerId())
            .setTransactionDate(transactionSpec.getTransactionHeaderDataEntity().getTransactionDate())
            .setTransactionDetails(convertDetailDataEntitiesToModels(transactionSpec))
            .setTimestamp(new Date(transactionSpec.getTransactionHeaderDataEntity().getTimestamp().getTime()))
            .setNote(transactionSpec.getTransactionHeaderDataEntity().getNote())
            ;
  }

  public static List<TransactionDetail> convertDetailDataEntitiesToModels(TransactionSpec dataEntity){
    return dataEntity.getTransactionDetailDataEntityList().stream()
            .map(
                  transactionDetailDataEntity -> new TransactionDetail()
                    .setItemCode(transactionDetailDataEntity.getItemCode())
                    .setNote(transactionDetailDataEntity.getNote())
                    .setQuantity(transactionDetailDataEntity.getQuantity())
                    .setPrice(transactionDetailDataEntity.getPrice())
                    .setTransactionHeaderId(dataEntity.getTransactionHeaderDataEntity().getId())
                    .setTimestamp(new Date(transactionDetailDataEntity.getTimestamp().getTime()))
                    .setNote(transactionDetailDataEntity.getNote())
            ).collect(Collectors.toList());
  }
}

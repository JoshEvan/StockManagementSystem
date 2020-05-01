package com.joshua.StockManagementSystem.joseph_impl.infrastructure.adapter;

import com.joshua.StockManagementSystem.joseph_api.api.payload.UpsertTransactionHeaderRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.model.TransactionDetail;
import com.joshua.StockManagementSystem.joseph_api.model.TransactionHeader;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.dao.spec.TransactionSpec;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout.ProductionDataEntity;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout.TransactionDetailDataEntity;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout.TransactionHeaderDataEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
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
              .setNote(resultSet.getString(TransactionHeaderDataEntity.NOTE));
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

  public static List<TransactionHeader> convertDataEntitiesToModels(List<TransactionSpec> dataEntities){
    return dataEntities.stream()
            .map(
                    transactionSpec -> {
                      return new TransactionHeader()
                              .setId(transactionSpec.getTransactionHeaderDataEntity().getId())
                              .setCustomerId(transactionSpec.getTransactionHeaderDataEntity().getCustomerId())
                              .setPaymentId(transactionSpec.getTransactionHeaderDataEntity().getPaymentId())
                              .setPaymentStatus(transactionSpec.getTransactionHeaderDataEntity().getPaymentStatus())
                              .setTransactionDate(transactionSpec.getTransactionHeaderDataEntity().getTransactionDate())
                              .setTransactionDetails(
                                      convertDetailDataEntitiesToModels(transactionSpec)
                              );
                    }
            ).collect(Collectors.toList());
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
            ).collect(Collectors.toList());
  }
}

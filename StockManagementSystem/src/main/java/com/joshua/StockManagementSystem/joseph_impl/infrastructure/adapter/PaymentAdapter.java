package com.joshua.StockManagementSystem.joseph_impl.infrastructure.adapter;

import com.joshua.StockManagementSystem.joseph_api.api.payload.UpsertPaymentRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.model.Payment;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout.PaymentDataEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class PaymentAdapter {
  public static PaymentDataEntity convertResultSetToDataEntity(ResultSet resultSet) {
    try {
      return new PaymentDataEntity()
              .setId(resultSet.getString(PaymentDataEntity.ID))
              .setPayment_type(resultSet.getString(PaymentDataEntity.TYPE));
    } catch (SQLException throwables) {
      return new PaymentDataEntity();
    }
  }

  public static PaymentDataEntity convertUpsertPayloadToDataEntity(UpsertPaymentRequestPayload upsertPaymentRequestPayload) {

    return new PaymentDataEntity()
            .setId(upsertPaymentRequestPayload.getId())
            .setPayment_type(upsertPaymentRequestPayload.getPaymentType());
  }

  public static List<Payment> convertDataEntitiesToModels(List<PaymentDataEntity> dataEntities){
    return dataEntities.stream()
            .map(
                    paymentDataEntity -> new Payment()
                            .setId(paymentDataEntity.getId())
                            .setPaymentType(paymentDataEntity.getPayment_type())
            ).collect(Collectors.toList());
  }
}

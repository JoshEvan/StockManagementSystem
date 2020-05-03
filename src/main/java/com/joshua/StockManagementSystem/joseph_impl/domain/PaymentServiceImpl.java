package com.joshua.StockManagementSystem.joseph_impl.domain;

import com.joshua.StockManagementSystem.joseph_api.api.payload.upsert.UpsertPaymentRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.domain.PaymentService;
import com.joshua.StockManagementSystem.joseph_api.infrastructure.dao.PaymentDAO;
import com.joshua.StockManagementSystem.joseph_api.model.Payment;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.PostgresHelper;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout.PaymentDataEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static com.joshua.StockManagementSystem.joseph_impl.infrastructure.PostgresHelper.*;
import static com.joshua.StockManagementSystem.joseph_impl.infrastructure.adapter.PaymentAdapter.*;

@Component("paymentV1Service")
@Service
public class PaymentServiceImpl implements PaymentService {
  private final PaymentDAO paymentDAO;

  @Autowired
  public PaymentServiceImpl(@Qualifier("postgresPayment") PaymentDAO paymentDAO) {
    this.paymentDAO = paymentDAO;
  }

  @Override
  public List<String> insert(UpsertPaymentRequestPayload upsertPaymentRequestPayload) {
    List<String> stats = new LinkedList<>();
    if(paymentDAO.insert(convertUpsertPayloadToDataEntity(upsertPaymentRequestPayload)) == 1){
      stats.add(ITEM+ upsertPaymentRequestPayload.getPaymentType()+ SUCCESS+" "+ PostgresHelper.INSERTED);
    }else{
      stats.add(ITEM+ upsertPaymentRequestPayload.getPaymentType()+ FAIL+" "+ PostgresHelper.INSERTED);
    }
    return stats;
  }

  @Override
  public List<Payment> index() {
    return convertDataEntitiesToModels(paymentDAO.index());
  }

  @Override
  public Payment show(String id) {
    PaymentDataEntity dataEntity = paymentDAO.show(id).orElse(null);
    return (dataEntity == null) ? null :
            convertDataEntitiesToModels(Collections.singletonList(dataEntity)).get(0);

  }

  @Override
  public List<String> update(UpsertPaymentRequestPayload upsertPaymentRequestPayload) {
    List<String> stats = new LinkedList<>();
    if(paymentDAO.update(convertUpsertPayloadToDataEntity(upsertPaymentRequestPayload)) == 1){
      stats.add(ITEM+ upsertPaymentRequestPayload.getPaymentType()+ SUCCESS+" "+ PostgresHelper.UPDATED);
    }else{
      stats.add(ITEM+ upsertPaymentRequestPayload.getPaymentType()+ FAIL+" "+ PostgresHelper.UPDATED);
    }
    return stats;
  }

  @Override
  public List<String> delete(String id) {
    List<String> stats = new LinkedList<>();
    if(paymentDAO.delete(id) == 1){
      stats.add( ITEM+ id + SUCCESS+" "+ PostgresHelper.REMOVED);
    }else{
      stats.add(ITEM+ id + FAIL+" "+ PostgresHelper.REMOVED);
    }
    return stats;
  }
}

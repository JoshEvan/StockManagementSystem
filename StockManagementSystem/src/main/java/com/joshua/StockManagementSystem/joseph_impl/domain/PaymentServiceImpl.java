package com.joshua.StockManagementSystem.joseph_impl.domain;

import com.joshua.StockManagementSystem.joseph_api.api.payload.upsert.UpsertPaymentRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.domain.PaymentService;
import com.joshua.StockManagementSystem.joseph_api.infrastructure.dao.PaymentDAO;
import com.joshua.StockManagementSystem.joseph_api.model.Payment;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.PostgresHelper;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout.PaymentDataEntity;
import com.joshua.StockManagementSystem.util.Pair;
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
  public Pair<Boolean,List<String>> insert(UpsertPaymentRequestPayload upsertPaymentRequestPayload) {
    Integer flag = paymentDAO.insert(convertUpsertPayloadToDataEntity(upsertPaymentRequestPayload));
    return new Pair<>(
            (flag ==1 ),
            Collections.singletonList(PAYTYPE+ upsertPaymentRequestPayload.getPaymentType()+ ((flag == 1) ? SUCCESS : FAIL)+ PostgresHelper.INSERTED)
    );
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
  public Pair<Boolean,List<String>> update(UpsertPaymentRequestPayload upsertPaymentRequestPayload) {
    Integer flag = paymentDAO.update(convertUpsertPayloadToDataEntity(upsertPaymentRequestPayload));
    return new Pair<>(
            (flag ==1 ),
            Collections.singletonList(PAYTYPE+ upsertPaymentRequestPayload.getPaymentType()+ ((flag == 1) ? SUCCESS : FAIL)+ UPDATED)
    );
  }

  @Override
  public Pair<Boolean,List<String>> delete(String id) {
    Integer flag = paymentDAO.delete(id);
    return new Pair<>(
            (flag ==1 ),
            Collections.singletonList(PAYTYPE+ id+ ((flag == 1) ? SUCCESS : FAIL)+ REMOVED)
    );
  }
}

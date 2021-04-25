package com.joshua.StockManagementSystem.joseph_impl.api;


import com.joshua.StockManagementSystem.joseph_api.api.PaymentAPIController;
import com.joshua.StockManagementSystem.joseph_api.api.payload.ResponsePayload;
import com.joshua.StockManagementSystem.joseph_api.api.payload.index.IndexPayTypeResponsePayload;
import com.joshua.StockManagementSystem.joseph_api.api.payload.upsert.UpsertPaymentRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.domain.PaymentService;
import com.joshua.StockManagementSystem.joseph_api.model.Payment;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.HttpStatus;
import com.joshua.StockManagementSystem.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;

@Component("paymentV1API")
public class PaymentAPIControllerImpl implements PaymentAPIController {

  private final PaymentService paymentService;

  @Autowired
  public PaymentAPIControllerImpl(@Qualifier("paymentV1Service") PaymentService paymentService) {
    this.paymentService = paymentService;
  }

  @Override
  public ResponsePayload insert(@NotNull UpsertPaymentRequestPayload upsertPaymentRequestPayload) {
    Pair<Boolean, List<String>> ret = paymentService.insert(upsertPaymentRequestPayload);
    return new ResponsePayload().setStatus((ret.getKey() ? HttpStatus.SUCCESS : HttpStatus.FAIL).toString())
            .setMessage(ret.getValue());
  }

  @Override
  public IndexPayTypeResponsePayload index() {
    return new IndexPayTypeResponsePayload().setPaymentTypes(paymentService.index());
  }

  @Override
  public Payment show(@NotNull String id) {
    return paymentService.show(id);
  }

  @Override
  public ResponsePayload update(@NotNull UpsertPaymentRequestPayload upsertPaymentRequestPayload) {
    Pair<Boolean,List<String>> ret = paymentService.update(upsertPaymentRequestPayload);
    return new ResponsePayload().setStatus((ret.getKey() ? HttpStatus.SUCCESS : HttpStatus.FAIL).toString())
            .setMessage(ret.getValue());
  }

  @Override
  public ResponsePayload delete(@NotNull String id) {
    Pair<Boolean,List<String>> ret = paymentService.delete(id);
    return new ResponsePayload().setStatus((ret.getKey() ? HttpStatus.SUCCESS : HttpStatus.FAIL).toString()).setMessage(ret.getValue());
  }
}

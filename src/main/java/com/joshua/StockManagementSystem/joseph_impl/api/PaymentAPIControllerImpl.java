package com.joshua.StockManagementSystem.joseph_impl.api;

import com.joshua.StockManagementSystem.joseph_api.api.PaymentAPIController;
import com.joshua.StockManagementSystem.joseph_api.api.payload.UpsertPaymentRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.domain.PaymentService;
import com.joshua.StockManagementSystem.joseph_api.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;

@Component("paymentAPIV1")
public class PaymentAPIControllerImpl implements PaymentAPIController {

  private final PaymentService paymentService;

  @Autowired
  public PaymentAPIControllerImpl(@Qualifier("paymentV1Service") PaymentService paymentService) {
    this.paymentService = paymentService;
  }

  @Override
  public List<String> insert(@NotNull UpsertPaymentRequestPayload upsertPaymentRequestPayload) {
    return paymentService.insert(upsertPaymentRequestPayload);
  }

  @Override
  public List<Payment> index() {
    return paymentService.index();
  }

  @Override
  public Payment show(@NotNull String id) {
    return paymentService.show(id);
  }

  @Override
  public List<String> update(@NotNull UpsertPaymentRequestPayload upsertPaymentRequestPayload) {
    return paymentService.update(upsertPaymentRequestPayload);
  }

  @Override
  public List<String> delete(@NotNull String id) {
    return paymentService.delete(id);
  }
}

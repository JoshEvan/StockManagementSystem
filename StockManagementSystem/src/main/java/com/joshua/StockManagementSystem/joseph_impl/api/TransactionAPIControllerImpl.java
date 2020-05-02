package com.joshua.StockManagementSystem.joseph_impl.api;

import com.joshua.StockManagementSystem.joseph_api.api.TransactionAPIController;
import com.joshua.StockManagementSystem.joseph_api.api.payload.UpsertTransactionHeaderRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.domain.TransactionService;
import com.joshua.StockManagementSystem.joseph_api.model.TransactionHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;

@Component("transactionV1API")
public class TransactionAPIControllerImpl implements TransactionAPIController {
  private final TransactionService transactionService;

  @Autowired
  public TransactionAPIControllerImpl(@Qualifier("transactionV1Service") TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  @Override
  public List<String> insert(@NotNull UpsertTransactionHeaderRequestPayload upsertTransactionHeaderRequestPayload) {
      return transactionService.insert(upsertTransactionHeaderRequestPayload);
  }

  @Override
  public List<TransactionHeader> index() {
    return transactionService.index();
  }

  @Override
  public TransactionHeader show(@NotNull String id) {
    return transactionService.show(id);
  }

  @Override
  public List<String> update(@NotNull UpsertTransactionHeaderRequestPayload upsertTransactionHeaderRequestPayload) {
    return transactionService.update(upsertTransactionHeaderRequestPayload);
  }

  @Override
  public List<String> delete(@NotNull String id) {
    return transactionService.delete(id);
  }
}

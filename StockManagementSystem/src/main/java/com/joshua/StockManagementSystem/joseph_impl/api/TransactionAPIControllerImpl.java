package com.joshua.StockManagementSystem.joseph_impl.api;

import com.joshua.StockManagementSystem.joseph_api.api.TransactionAPIController;
import com.joshua.StockManagementSystem.joseph_api.api.payload.index.IndexTransactionRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.api.payload.upsert.UpsertTransactionHeaderRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.domain.TransactionService;
import com.joshua.StockManagementSystem.joseph_api.model.TransactionHeader;
import org.flywaydb.core.internal.util.FileCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
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
  public List<TransactionHeader> index(IndexTransactionRequestPayload indexTransactionRequestPayload) {
    return transactionService.index(indexTransactionRequestPayload);
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

  @Override
  public void generateReport(
          HttpServletResponse response,
          @NotNull IndexTransactionRequestPayload indexTransactionRequestPayload) throws IOException {
    transactionService.generateReport(indexTransactionRequestPayload);
    URL res = getClass().getClassLoader().getResource("web/generatedpdf/TransactionReport.pdf");

//    File file = new File(res.getFile());
    File file = new File(System.getProperty("user.dir")+"/web/generatedpdf/TransactionReport.pdf");
    InputStream in = new FileInputStream(file);

    response.setContentType("application/pdf");
    response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
    response.setHeader("Content-Length", String.valueOf(file.length()));
    FileCopyUtils.copy(in, response.getOutputStream());

  }
}

package com.joshua.StockManagementSystem.joseph_impl.api;

import com.joshua.StockManagementSystem.joseph_api.api.TransactionAPIController;
import com.joshua.StockManagementSystem.joseph_api.api.payload.ResponsePayload;
import com.joshua.StockManagementSystem.joseph_api.api.payload.index.IndexTransactionRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.api.payload.index.IndexTransactionResponsePayload;
import com.joshua.StockManagementSystem.joseph_api.api.payload.upsert.UpsertTransactionHeaderRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.domain.TransactionService;
import com.joshua.StockManagementSystem.joseph_api.model.TransactionHeader;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.HttpStatus;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.PostgresHelper;
import com.sun.org.apache.xpath.internal.operations.Bool;
import com.joshua.StockManagementSystem.util.Pair;
import org.flywaydb.core.internal.util.FileCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

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
  public ResponsePayload insert(@NotNull UpsertTransactionHeaderRequestPayload upsertTransactionHeaderRequestPayload) {
      Pair<Boolean,List<String>> ret = transactionService.insert(upsertTransactionHeaderRequestPayload);
      return new ResponsePayload().setStatus((ret.getKey() ? HttpStatus.SUCCESS :HttpStatus.FAIL).toString())
              .setMessage(ret.getValue());
  }

  @Override
  public IndexTransactionResponsePayload index(IndexTransactionRequestPayload indexTransactionRequestPayload) {
    return new IndexTransactionResponsePayload().setTransactions(transactionService.index(indexTransactionRequestPayload));
  }

  @Override
  public TransactionHeader show(@NotNull String id) {
    return transactionService.show(id);
  }

  @Override
  public ResponsePayload update(@NotNull UpsertTransactionHeaderRequestPayload upsertTransactionHeaderRequestPayload) {
    Pair<Boolean,List<String>> ret = transactionService.update(upsertTransactionHeaderRequestPayload);
    return new ResponsePayload().setStatus((ret.getKey() ? HttpStatus.SUCCESS : HttpStatus.FAIL).toString())
            .setMessage(ret.getValue());
  }

  @Override
  public ResponsePayload delete(@NotNull String id) {
    Pair<Boolean,List<String>> ret = transactionService.delete(id);
    return new ResponsePayload().setMessage(ret.getValue())
            .setStatus((ret.getKey() ? HttpStatus.SUCCESS : HttpStatus.FAIL).toString());
  }

  @Override
  public void generateReport(
          HttpServletResponse response,
          @NotNull IndexTransactionRequestPayload indexTransactionRequestPayload) throws IOException {
    transactionService.generateReport(indexTransactionRequestPayload);
    URL res = getClass().getClassLoader().getResource(PostgresHelper.PDF_PATH+ PostgresHelper.TRANS_PDF_FILENAME);

//    File file = new File(res.getFile());
    File file = new File(System.getProperty("user.dir")+PostgresHelper.PDF_PATH+ PostgresHelper.TRANS_PDF_FILENAME);
    InputStream in = new FileInputStream(file);

    response.setContentType("application/pdf");
    response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
    response.setHeader("Content-Length", String.valueOf(file.length()));
    FileCopyUtils.copy(in, response.getOutputStream());

  }
}

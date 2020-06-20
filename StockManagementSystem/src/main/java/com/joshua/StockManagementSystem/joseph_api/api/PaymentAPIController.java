package com.joshua.StockManagementSystem.joseph_api.api;

import com.joshua.StockManagementSystem.joseph_api.api.payload.ResponsePayload;
import com.joshua.StockManagementSystem.joseph_api.api.payload.index.IndexPayTypeResponsePayload;
import com.joshua.StockManagementSystem.joseph_api.api.payload.upsert.UpsertPaymentRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.model.Payment;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import javax.xml.ws.Response;
import java.util.List;

@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping("api/v1/joseph/payment")
@RestController
@EnableAutoConfiguration
@Component("paymentV1API")
public interface PaymentAPIController {
  @PostMapping("/insert")
  public ResponsePayload insert(@NotNull @RequestBody UpsertPaymentRequestPayload upsertPaymentRequestPayload);

  @GetMapping(value = "/", produces = "application/json")
  public @ResponseBody
  IndexPayTypeResponsePayload index();

  @GetMapping(value = "/show/{id}", produces = "application/json")
  public @ResponseBody
  Payment show(@NotNull @PathVariable("id") String id);

  @PutMapping("/update")
  public ResponsePayload update(@NotNull @RequestBody UpsertPaymentRequestPayload upsertPaymentRequestPayload);

  @DeleteMapping("/delete/{id}")
  public ResponsePayload delete(@NotNull @PathVariable("id") String id);
}

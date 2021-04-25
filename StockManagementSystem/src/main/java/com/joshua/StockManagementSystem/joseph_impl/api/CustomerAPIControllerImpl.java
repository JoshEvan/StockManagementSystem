package com.joshua.StockManagementSystem.joseph_impl.api;

import com.joshua.StockManagementSystem.joseph_api.api.CustomerAPIController;
import com.joshua.StockManagementSystem.joseph_api.api.payload.ResponsePayload;
import com.joshua.StockManagementSystem.joseph_api.api.payload.index.IndexCustomerResponsePayload;
import com.joshua.StockManagementSystem.joseph_api.api.payload.upsert.UpsertCustomerRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.domain.CustomerService;
import com.joshua.StockManagementSystem.joseph_api.model.Customer;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.HttpStatus;
import com.joshua.StockManagementSystem.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotNull;
import java.util.List;

@Component("custAPIV1")
public class CustomerAPIControllerImpl implements CustomerAPIController {
  private  final CustomerService customerService;

  @Autowired
  public CustomerAPIControllerImpl(@Qualifier("custV1Service") CustomerService customerService) {
    this.customerService = customerService;
  }

  @Override
  public ResponsePayload insertCustomer(@NotNull @RequestBody UpsertCustomerRequestPayload upsertCustomerRequestPayload) {
    Pair<Boolean,List<String>> res =customerService.insertCustomer(upsertCustomerRequestPayload);
    return new ResponsePayload().setMessage(res.getValue()).setStatus(res.getKey() ? HttpStatus.SUCCESS.toString() : HttpStatus.FAIL.toString());
  }

  @Override
  public @ResponseBody
  IndexCustomerResponsePayload indexCustomer() {
      return new IndexCustomerResponsePayload().setCustomers(customerService.indexCustomer());
  }

  @Override
  public Customer showCustomer(String id) {
    return customerService.showCustomer(id);
  }

  @Override
  public ResponsePayload updateCustomer(@NotNull UpsertCustomerRequestPayload upsertCustomerRequestPayload) {
    Pair<Boolean, List<String>> res = customerService.updateCustomer(upsertCustomerRequestPayload);
    return new ResponsePayload().setStatus(res.getKey() ? HttpStatus.SUCCESS.toString() : HttpStatus.FAIL.toString()).setMessage(res.getValue());
  }

  @Override
  public ResponsePayload deleteCustomer(@NotNull String id) {
    Pair<Boolean,List<String>> res = customerService.deleteCustomer(id);
    return new ResponsePayload().setStatus(res.getKey() ? HttpStatus.SUCCESS.toString() : HttpStatus.FAIL.toString()).setMessage(res.getValue());
  }


}

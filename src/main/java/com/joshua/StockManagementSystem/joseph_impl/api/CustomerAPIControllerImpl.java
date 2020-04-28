package com.joshua.StockManagementSystem.joseph_impl.api;

import com.joshua.StockManagementSystem.joseph_api.api.CustomerAPIController;
import com.joshua.StockManagementSystem.joseph_api.api.payload.UpsertCustomerRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.domain.JosephService;
import com.joshua.StockManagementSystem.joseph_api.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotNull;
import java.util.List;

@Component("apiV1")
public class CustomerAPIControllerImpl implements CustomerAPIController {
  private  final JosephService josephService;

  @Autowired
  public CustomerAPIControllerImpl(@Qualifier("v1") JosephService josephService) {
    this.josephService = josephService;
  }

  @Override
  public List<String> insertCustomer(@NotNull @RequestBody UpsertCustomerRequestPayload upsertCustomerRequestPayload) {
    System.out.println(upsertCustomerRequestPayload.getName());
    System.out.println(upsertCustomerRequestPayload.getDescription());
    return josephService.insertCustomer(upsertCustomerRequestPayload);
  }
  //    @GetMapping @RequestMapping(value="/index", method = RequestMethod.GET, produces="application/json")

  @Override
  public @ResponseBody
  List<Customer> indexCustomer() {
    return josephService.indexCustomer();
  }

  @Override
  public List<String> updateCustomer(@NotNull UpsertCustomerRequestPayload upsertCustomerRequestPayload) {
    return josephService.updateCustomer(upsertCustomerRequestPayload);
  }

  @Override
  public List<String> deleteCustomer(@NotNull String idCust) {
    return josephService.deleteCustomer(idCust);
  }


}

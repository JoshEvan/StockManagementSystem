package com.joshua.StockManagementSystem.joseph_api.api;

import com.joshua.StockManagementSystem.joseph_api.api.payload.UpsertCustomerRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.model.Customer;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("api/v1/joseph/customer")
@RestController
@EnableAutoConfiguration
@Component("apiV1")
public interface CustomerAPIController {
    @PostMapping("/insert")
    public List<String> insertCustomer(@NotNull @RequestBody UpsertCustomerRequestPayload upsertCustomerRequestPayload);

    @GetMapping(value = "/", produces = "application/json")
    public @ResponseBody List<Customer> indexCustomer();

    @PutMapping("/update")
    public List<String> updateCustomer(@NotNull @RequestBody UpsertCustomerRequestPayload upsertCustomerRequestPayload);

    @DeleteMapping("/delete/{id}")
    public List<String> deleteCustomer(@NotNull @PathVariable("id") String idCust);
}

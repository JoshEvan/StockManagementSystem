package com.joshua.StockManagementSystem.joseph_api.api;

import com.joshua.StockManagementSystem.joseph_api.api.payload.ResponsePayload;
import com.joshua.StockManagementSystem.joseph_api.api.payload.index.IndexCustomerResponsePayload;
import com.joshua.StockManagementSystem.joseph_api.api.payload.upsert.UpsertCustomerRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.model.Customer;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/v1/joseph/customer")
@RestController
@EnableAutoConfiguration
@Component("custAPIV1")
public interface CustomerAPIController {
    @PostMapping("/insert")
    public ResponsePayload insertCustomer(@NotNull @RequestBody UpsertCustomerRequestPayload upsertCustomerRequestPayload);

    @GetMapping(value = "/", produces = "application/json")
    public @ResponseBody
    IndexCustomerResponsePayload indexCustomer();

    @GetMapping(value = "/show/{id}", produces = "application/json")
    public @ResponseBody Customer showCustomer(@NotNull @PathVariable("id") String id);

    @PutMapping("/update")
    public List<String> updateCustomer(@NotNull @RequestBody UpsertCustomerRequestPayload upsertCustomerRequestPayload);

    @DeleteMapping("/delete/{id}")
    public ResponsePayload deleteCustomer(@NotNull @PathVariable("id") String id);
}

package com.joshua.StockManagementSystem.joseph_api.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/joseph")
@RestController
public interface JosephAPI {
    @PostMapping
    void addCustomer(

    );
}

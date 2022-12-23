package com.joshua.StockManagementSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
	org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class}
)
public class StockManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockManagementSystemApplication.class, args);
	}

}

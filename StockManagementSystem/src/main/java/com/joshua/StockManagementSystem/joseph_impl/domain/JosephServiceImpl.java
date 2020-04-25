package com.joshua.StockManagementSystem.joseph_impl.domain;

import com.joshua.StockManagementSystem.joseph_api.dao.CustomerDAO;
import com.joshua.StockManagementSystem.joseph_api.domain.JosephService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class JosephServiceImpl implements JosephService {
    private final CustomerDAO customerDAO;

    @Autowired
    public JosephServiceImpl(@Qualifier("postgres") CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

}

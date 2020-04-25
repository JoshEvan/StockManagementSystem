package com.joshua.StockManagementSystem.joseph_impl.dao;

import com.joshua.StockManagementSystem.joseph_api.dao.CustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("postgres")
public class CustomerDataAccessService implements CustomerDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CustomerDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


}

package com.joshua.StockManagementSystem.joseph_impl.infrastructure.dao;

import com.joshua.StockManagementSystem.joseph_api.infrastructure.dao.CustomerDAO;
import com.joshua.StockManagementSystem.joseph_api.model.Customer;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.PostgresHelper;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.adapter.CustomerAdapter;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout.CustomerDataEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository("postgres")
public class CustomerDataAccessService implements CustomerDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CustomerDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer insertCustomer(CustomerDataEntity customer) {
        final String sql = PostgresHelper.insertOperation(customer);
        return jdbcTemplate.update(sql
                ,customer.getId()
                ,customer.getName()
                ,customer.getDescription()
                ,customer.getContact()
        );
    }

    @Override
    public List<CustomerDataEntity> indexCustomer(){
        final String sql = PostgresHelper.selectOperation(new CustomerDataEntity());

        return jdbcTemplate.query(
            sql, ((resultSet, i) -> {
                CustomerDataEntity dataEntity = CustomerAdapter.convertResultSetToDataEntity(resultSet);
                return dataEntity;
            })
        );
    }

    @Override
    public Integer updateCustomer(CustomerDataEntity customer) {
        HashMap<String,Object> setter = new HashMap<>();
        setter.put(CustomerDataEntity.NAME, customer.getName());
        setter.put(CustomerDataEntity.CONTACT, customer.getContact());
        setter.put(CustomerDataEntity.DESCRIPTION, customer.getDescription());
        final String sql = PostgresHelper.updateOperation(customer,setter,"id = \'" +customer.getId()+"\'");
        return jdbcTemplate.update(sql);
    }

    @Override
    public Integer deleteCustomer(String idCust) {
        final  String sql = PostgresHelper.deleteOperation(new CustomerDataEntity(),"WHERE "+CustomerDataEntity.ID+" = \'"+idCust+'\'');
        return jdbcTemplate.update(sql);
    }

}

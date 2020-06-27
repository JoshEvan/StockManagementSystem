package com.joshua.StockManagementSystem.joseph_impl.infrastructure.dao;

import com.joshua.StockManagementSystem.joseph_api.infrastructure.dao.CustomerDAO;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.PostgresHelper;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.adapter.CustomerAdapter;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout.CustomerDataEntity;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout.ItemDataEntity;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout.PaymentDataEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository("postgresCust")
public class CustomerDataAccessService implements CustomerDAO {
    private final JdbcTemplate jdbcTemplate;
    private final Logger log = LoggerFactory.getLogger(CustomerDataAccessService.class);

    @Autowired
    public CustomerDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer insert(CustomerDataEntity customer) {
        if(show(customer.getId()) != null){
            return update(customer);
        }
        final String sql = PostgresHelper.insertOperation(customer);
        return jdbcTemplate.update(sql
                ,customer.getId()
                ,customer.getName()
                ,customer.getDescription()
                ,customer.getContact()
        );
    }

    @Override
    public List<CustomerDataEntity> index(){
        final String sql = PostgresHelper.selectOperation(new CustomerDataEntity())
                + " WHERE "+ CustomerDataEntity.ISACTIVE+" = true";

        return jdbcTemplate.query(
            sql, ((resultSet, i) -> {
                CustomerDataEntity dataEntity = CustomerAdapter.convertResultSetToDataEntity(resultSet);
                return dataEntity;
            })
        );
    }

    @Override
    public Optional<CustomerDataEntity> show(String id) {
        final String sql = PostgresHelper.selectOperation(new CustomerDataEntity())
                + " WHERE "+CustomerDataEntity.ID +" = ?";

        try{
            CustomerDataEntity c =  jdbcTemplate.queryForObject(sql, new Object[]{id}, ((resultSet, i) -> {
                return CustomerAdapter.convertResultSetToDataEntity(resultSet);
            }));
            return Optional.ofNullable(c);
        }catch (Exception e){
            log.error("no customer with id"+id,e);
            return null;
        }
    }

    @Override
    public Integer update(CustomerDataEntity customer) {
        HashMap<String,Object> setter = new HashMap<>();
        setter.put(CustomerDataEntity.NAME, customer.getName());
        setter.put(CustomerDataEntity.CONTACT, customer.getContact());
        setter.put(CustomerDataEntity.DESCRIPTION, customer.getDescription());
        setter.put(CustomerDataEntity.ISACTIVE, true);
        final String sql = PostgresHelper.updateOperation(customer,setter,"id = \'" +customer.getId()+"\'");
        return jdbcTemplate.update(sql);
    }

    @Override
    public Integer delete(String idCust) {
        CustomerDataEntity customerDataEntity = show(idCust).orElse(null);
        if(customerDataEntity == null) return 0;
        HashMap<String,Object> setter = new HashMap<>();
        setter.put(CustomerDataEntity.ISACTIVE, false);
        final String sql = PostgresHelper.updateOperation(customerDataEntity,
                setter,CustomerDataEntity.ID+" = \'" +customerDataEntity.getId()+"\'");
        return jdbcTemplate.update(sql);
    }

}

package com.joshua.StockManagementSystem.joseph_impl.domain;

import com.joshua.StockManagementSystem.joseph_api.api.payload.UpsertCustomerRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.infrastructure.dao.CustomerDAO;
import com.joshua.StockManagementSystem.joseph_api.domain.CustomerService;
import com.joshua.StockManagementSystem.joseph_api.model.Customer;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.PostgresHelper;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.adapter.CustomerAdapter;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout.CustomerDataEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static com.joshua.StockManagementSystem.joseph_impl.infrastructure.PostgresHelper.*;

@Component("custV1Service")
@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerDAO customerDAO;

    @Autowired
    public CustomerServiceImpl(@Qualifier("postgresCust") CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @Override
    public List<String> insertCustomer(UpsertCustomerRequestPayload upsertCustomerRequestPayload) {
        List<String> stats = new LinkedList<>();
        if(customerDAO.insertCustomer(CustomerAdapter.convertUpsertPayloadToDataEntity(upsertCustomerRequestPayload)) == 1){
            stats.add(ITEM+ upsertCustomerRequestPayload.getName()+ SUCCESS+" "+ PostgresHelper.INSERTED);
        }else{
            stats.add(ITEM+ upsertCustomerRequestPayload.getName()+ FAIL+" "+ PostgresHelper.INSERTED);
        }
        return stats;
    }

    @Override
    public List<Customer> indexCustomer() {
        return CustomerAdapter.convertDataEntitiesToModels(customerDAO.indexCustomer());
    }

    @Override
    public Customer showCustomer(String id) {
        CustomerDataEntity dataEntity = customerDAO.showCustomer(id).orElse(null);
        return (dataEntity == null) ? null :
            CustomerAdapter.convertDataEntitiesToModels(Collections.singletonList(dataEntity)).get(0);
    }

    @Override
    public List<String> updateCustomer(UpsertCustomerRequestPayload upsertCustomerRequestPayload) {
        List<String> stats = new LinkedList<>();
        if(customerDAO.updateCustomer(CustomerAdapter.convertUpsertPayloadToDataEntity(upsertCustomerRequestPayload)) == 1){
            stats.add(ITEM+ upsertCustomerRequestPayload.getName()+ SUCCESS+" "+ PostgresHelper.UPDATED);
        }else{
            stats.add(ITEM+ upsertCustomerRequestPayload.getName()+ FAIL+" "+ PostgresHelper.UPDATED);
        }
        return stats;
    }

    @Override
    public List<String> deleteCustomer(String id) {
        List<String> stats = new LinkedList<>();
        if(customerDAO.deleteCustomer(id) == 1){
            stats.add(ITEM+ id + SUCCESS+" "+ PostgresHelper.REMOVED);
        }else{
            stats.add(ITEM+ id + FAIL+" "+ PostgresHelper.REMOVED);
        }
        return stats;
    }
}

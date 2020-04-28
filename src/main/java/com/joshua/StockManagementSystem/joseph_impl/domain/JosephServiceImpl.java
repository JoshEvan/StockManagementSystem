package com.joshua.StockManagementSystem.joseph_impl.domain;

import com.joshua.StockManagementSystem.joseph_api.api.payload.UpsertCustomerRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.infrastructure.dao.CustomerDAO;
import com.joshua.StockManagementSystem.joseph_api.domain.JosephService;
import com.joshua.StockManagementSystem.joseph_api.model.Customer;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.PostgresHelper;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.adapter.CustomerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

import static com.joshua.StockManagementSystem.joseph_impl.infrastructure.PostgresHelper.FAIL;
import static com.joshua.StockManagementSystem.joseph_impl.infrastructure.PostgresHelper.SUCCESS;

@Component("v1")
@Service
public class JosephServiceImpl implements JosephService {
    private final CustomerDAO customerDAO;

    @Autowired
    public JosephServiceImpl(@Qualifier("postgres") CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @Override
    public List<String> insertCustomer(UpsertCustomerRequestPayload upsertCustomerRequestPayload) {
        List<String> stats = new LinkedList<>();
        if(customerDAO.insertCustomer(CustomerAdapter.convertUpsertPayloadToDataEntity(upsertCustomerRequestPayload)) == 1){
            stats.add("Customer "+ upsertCustomerRequestPayload.getName()+ SUCCESS+" "+ PostgresHelper.UPDATED);
        }else{
            stats.add("Customer "+ upsertCustomerRequestPayload.getName()+ FAIL+" "+ PostgresHelper.UPDATED);
        }
        return stats;
    }

    @Override
    public List<Customer> indexCustomer() {
        return CustomerAdapter.convertDataEntitiesToModels(customerDAO.indexCustomer());
    }

    @Override
    public List<String> updateCustomer(UpsertCustomerRequestPayload upsertCustomerRequestPayload) {
        List<String> stats = new LinkedList<>();
        if(customerDAO.updateCustomer(CustomerAdapter.convertUpsertPayloadToDataEntity(upsertCustomerRequestPayload)) == 1){
            stats.add("Customer "+ upsertCustomerRequestPayload.getName()+ SUCCESS+" "+ PostgresHelper.UPDATED);
        }else{
            stats.add("Customer "+ upsertCustomerRequestPayload.getName()+ FAIL+" "+ PostgresHelper.UPDATED);
        }
        return stats;
    }

    @Override
    public List<String> deleteCustomer(String idCust) {
        List<String> stats = new LinkedList<>();
        if(customerDAO.deleteCustomer(idCust) == 1){
            stats.add("Customer "+ idCust+ SUCCESS+" "+ PostgresHelper.REMOVED);
        }else{
            stats.add("Customer "+ idCust+ FAIL+" "+ PostgresHelper.REMOVED);
        }
        return stats;
    }
}

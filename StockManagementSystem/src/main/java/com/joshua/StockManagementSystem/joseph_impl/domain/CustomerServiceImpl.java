package com.joshua.StockManagementSystem.joseph_impl.domain;

import com.joshua.StockManagementSystem.joseph_api.api.payload.index.IndexTransactionRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.api.payload.upsert.UpsertCustomerRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.infrastructure.dao.CustomerDAO;
import com.joshua.StockManagementSystem.joseph_api.domain.CustomerService;
import com.joshua.StockManagementSystem.joseph_api.infrastructure.dao.TransactionDAO;
import com.joshua.StockManagementSystem.joseph_api.model.Customer;
import com.joshua.StockManagementSystem.joseph_api.model.TransactionHeader;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.PostgresHelper;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.adapter.CustomerAdapter;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.adapter.TransactionAdapter;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.dao.spec.TransactionSpec;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout.CustomerDataEntity;
import com.joshua.StockManagementSystem.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static com.joshua.StockManagementSystem.joseph_impl.infrastructure.PostgresHelper.*;

@Component("custV1Service")
@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerDAO customerDAO;
    private final TransactionDAO transactionDAO;

    @Autowired
    public CustomerServiceImpl(@Qualifier("postgresCust") CustomerDAO customerDAO,
                               @Qualifier("postgresTransaction") TransactionDAO transactionDAO) {
        this.customerDAO = customerDAO;
        this.transactionDAO = transactionDAO;
    }

    @Override
    public Pair<Boolean,List<String>> insertCustomer(UpsertCustomerRequestPayload upsertCustomerRequestPayload) {
        Integer flag = customerDAO.insert(CustomerAdapter.convertUpsertPayloadToDataEntity(upsertCustomerRequestPayload));
        return new Pair<>((flag == 1), (flag == 1 ? Collections.singletonList(CUSTOMER+ upsertCustomerRequestPayload.getName()+ SUCCESS+" "+ PostgresHelper.INSERTED)
                : Collections.singletonList(CUSTOMER+ upsertCustomerRequestPayload.getName()+ FAIL+" "+ PostgresHelper.INSERTED)));
    }

    @Override
    public List<Customer> indexCustomer() {
        List<TransactionSpec> trans = transactionDAO.index(new IndexTransactionRequestPayload());
        List<TransactionHeader> transactionHeaders = TransactionAdapter.convertTransactionSpecsToModels(trans);
        HashMap<String, BigDecimal> spendPerCustomer = new HashMap<>();
        transactionHeaders.forEach(
            transactionHeader -> {
                if(spendPerCustomer.containsKey(transactionHeader.getCustomerId())){
                    spendPerCustomer.replace(
                        transactionHeader.getCustomerId(),
                        spendPerCustomer.get(transactionHeader.getCustomerId())
                                .add(transactionHeader.getTotalDec())
                    );
                }else{
                    spendPerCustomer.put(
                        transactionHeader.getCustomerId(),
                        transactionHeader.getTotalDec()
                    );
                }

            }
        );

        return CustomerAdapter.convertDataEntitiesToModels(customerDAO.index())
            .stream().map(
                customer -> customer.setTotalAmountSpend(formatCurrency(spendPerCustomer.get(customer.getId())))
            ).collect(Collectors.toList());
    }

    @Override
    public Customer showCustomer(String id) {

        CustomerDataEntity dataEntity = customerDAO.show(id).orElse(null);
        if(dataEntity == null) return null;

        List<TransactionHeader> transactionHeaders =
                TransactionAdapter.convertTransactionSpecsToModels(
                        transactionDAO.index(new IndexTransactionRequestPayload().setCustomerFilter(Collections.singletonList(id)))
                );
        BigDecimal amountSpend = BigDecimal.valueOf(0);
        for(TransactionHeader transactionHeader : transactionHeaders){
            amountSpend = amountSpend.add(transactionHeader.getTotalDec());
        }
        return CustomerAdapter.convertDataEntitiesToModels(Collections.singletonList(dataEntity))
                .get(0).setTotalAmountSpend(formatCurrency(amountSpend));
    }

    @Override
    public Pair<Boolean,List<String>> updateCustomer(UpsertCustomerRequestPayload upsertCustomerRequestPayload) {
        Integer flag = customerDAO.update(CustomerAdapter.convertUpsertPayloadToDataEntity(upsertCustomerRequestPayload));
        return new Pair<>(flag == 1, (flag == 1 ? Collections.singletonList(CUSTOMER+ upsertCustomerRequestPayload.getName()+ SUCCESS+" "+ PostgresHelper.UPDATED)
        : Collections.singletonList(CUSTOMER+ upsertCustomerRequestPayload.getName()+ FAIL+" "+ PostgresHelper.UPDATED)));
    }

    @Override
    public Pair<Boolean,List<String>> deleteCustomer(String id) {
        Integer flag = customerDAO.delete(id);
        return new Pair<>((flag == 1), (flag == 1) ? Collections.singletonList((CUSTOMER+ id + SUCCESS+" "+ PostgresHelper.REMOVED))
            : Collections.singletonList(CUSTOMER+ id + FAIL+" "+ PostgresHelper.REMOVED));

    }
}

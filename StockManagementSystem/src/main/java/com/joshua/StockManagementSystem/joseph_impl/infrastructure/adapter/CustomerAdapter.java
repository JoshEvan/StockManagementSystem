package com.joshua.StockManagementSystem.joseph_impl.infrastructure.adapter;

import com.joshua.StockManagementSystem.joseph_api.api.payload.upsert.UpsertCustomerRequestPayload;
import com.joshua.StockManagementSystem.joseph_api.model.Customer;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout.CustomerDataEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerAdapter {
  public static CustomerDataEntity convertResultSetToDataEntity(ResultSet resultSet) {
    try {
      return new CustomerDataEntity()
        .setId(resultSet.getString(CustomerDataEntity.ID))
        .setName(resultSet.getString(CustomerDataEntity.NAME))
        .setContact(resultSet.getString(CustomerDataEntity.CONTACT))
        .setDescription(resultSet.getString(CustomerDataEntity.DESCRIPTION));
    } catch (SQLException throwables) {
      return new CustomerDataEntity();
    }
  }

  public static CustomerDataEntity convertUpsertPayloadToDataEntity(UpsertCustomerRequestPayload upsertCustomerRequestPayload) {

    return new CustomerDataEntity()
            .setId(upsertCustomerRequestPayload.getId())
            .setName(upsertCustomerRequestPayload.getName())
            .setContact(upsertCustomerRequestPayload.getDescription())
            .setDescription(upsertCustomerRequestPayload.getContact());
  }

  public static List<Customer> convertDataEntitiesToModels(List<CustomerDataEntity> dataEntities){
    return dataEntities.stream()
            .map(
              customerDataEntity -> new Customer().setId(customerDataEntity.getId())
                      .setName(customerDataEntity.getName())
                      .setContact(customerDataEntity.getContact())
                      .setDescription(customerDataEntity.getDescription())
            ).collect(Collectors.toList());
  }
}

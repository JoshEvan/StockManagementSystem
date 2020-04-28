package com.joshua.StockManagementSystem.joseph_api.api.payload;


public class UpsertCustomerRequestPayload {
    private String id, name, description,contact;

    public String getId() {
        return id;
    }

    public UpsertCustomerRequestPayload setId(String id) {
        this.id = id;return this;
    }

    public String getName() {
        return name;
    }

    public UpsertCustomerRequestPayload setName(String name) {
        this.name = name;return this;
    }

    public String getDescription() {
        return description;
    }

    public UpsertCustomerRequestPayload setDescription(String description) {
        this.description = description;return this;
    }

    public String getContact() {
        return contact;
    }

    public UpsertCustomerRequestPayload setContact(String contact) {
        this.contact = contact;return this;
    }
}

package com.joshua.StockManagementSystem.joseph_api.model;

public class Customer {
    private String id, name, description,contact, totalAmountSpend;

    public String getId() {
        return id;
    }

    public Customer setId(String id) {
        this.id = id;return this;
    }

    public String getName() {
        return name;
    }

    public Customer setName(String name) {
        this.name = name;return this;
    }

    public String getDescription() {
        return description;
    }

    public Customer setDescription(String description) {
        this.description = description;return this;
    }

    public String getContact() {
        return contact;
    }

    public Customer setContact(String contact) {
        this.contact = contact;return this;
    }

    public String getTotalAmountSpend() {
        return totalAmountSpend;
    }

    public Customer setTotalAmountSpend(String totalAmountSpend) {
        this.totalAmountSpend = totalAmountSpend;return this;
    }
}

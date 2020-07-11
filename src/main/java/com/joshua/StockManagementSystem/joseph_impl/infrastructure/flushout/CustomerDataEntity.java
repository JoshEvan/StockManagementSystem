package com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout;

public class CustomerDataEntity extends JosephDataEntity {

  private String id, name, description,contact;
  public static String ID = "id", NAME = "name", DESCRIPTION = "description",
  CONTACT = "contact";
  public CustomerDataEntity() {
    TABLE = "customers";
    numColumns = 4;
  }

  public String getId() {
    return id;
  }

  public CustomerDataEntity setId(String id) {
    this.id = id;return this;
  }

  public String getName() {
    return name;
  }

  public CustomerDataEntity setName(String name) {
    this.name = name;return this;
  }

  public String getDescription() {
    return description;
  }

  public CustomerDataEntity setDescription(String description) {
    this.description = description;return this;
  }

  public String getContact() {
    return contact;
  }

  public CustomerDataEntity setContact(String contact) {
    this.contact = contact;return this;
  }
}

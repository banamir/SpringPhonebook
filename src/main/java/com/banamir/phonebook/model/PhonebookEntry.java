package com.banamir.phonebook.model;


public class PhonebookEntry {

    Long id;

    PhonebookUser user;

    private String firstName;

    private String middleName;

    private String lastName;

    private String mobilePhone;

    private String homePhone;

    private String address;

    private String email;

    public Long getId() {
        return id;
    }

    public PhonebookEntry setId(Long id) {
        this.id = id;
        return this;
    }

    public PhonebookUser getUser() {
        return user;
    }

    public PhonebookEntry setUser(PhonebookUser user) {
        this.user = user;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public PhonebookEntry setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getMiddleName() {
        return middleName;
    }

    public PhonebookEntry setMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public PhonebookEntry setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public PhonebookEntry setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public PhonebookEntry setHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public PhonebookEntry setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public PhonebookEntry setEmail(String email) {
        this.email = email;
        return this;
    }
}

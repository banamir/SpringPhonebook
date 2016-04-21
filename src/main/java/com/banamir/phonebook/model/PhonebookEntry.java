package com.banamir.phonebook.model;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class PhonebookEntry {

    Long id;

    PhonebookUser user;

    @Size(min = 4)
    @NotNull
    private String firstName;

    @Size(min = 4)
    @NotNull
    private String middleName;

    @Size(min = 4)
    @NotNull
    private String lastName;


    @NotNull
    @Pattern(regexp = "^(\\+38)?0(\\(\\d{2}\\)|\\d{2})\\d{7}$", message = "Wrong format of phone number")
    private String mobilePhone;

    @Pattern(regexp = "^(\\+38)?0(\\(\\d{2}\\)|\\d{2})\\d{7}$", message = "Wrong format of phone number")
    private String homePhone;

    private String address;

    @Pattern(regexp = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$", message = "Wrong email address")
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

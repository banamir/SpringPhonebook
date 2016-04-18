package com.banamir.phonebook.model;


import org.springframework.security.core.userdetails.User;

public class PhonebookEntry {

    Long id;

    User user;

    private String firstName;

    private String middleName;

    private String lastName;

    private String mobilePhone;

    private String homePhone;

    private String address;

    private String email;
}

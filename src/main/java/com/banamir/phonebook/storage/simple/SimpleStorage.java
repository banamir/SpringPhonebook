package com.banamir.phonebook.storage.simple;


import com.banamir.phonebook.model.PhonebookEntry;
import com.banamir.phonebook.model.PhonebookUser;

import java.util.HashMap;
import java.util.List;

public class SimpleStorage {

    private String storageFileName;

    protected HashMap<Long, PhonebookEntry> entries;

    protected HashMap<Long, PhonebookUser> users;

    protected HashMap<Long, List<PhonebookEntry>> entiesByUser;


    public void load() {

    }

    public void upload() {

    }


}

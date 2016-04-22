package com.banamir.phonebook.storage.simple;

import com.banamir.phonebook.manager.PhonebookManager;
import com.banamir.phonebook.model.PhonebookEntry;
import com.banamir.phonebook.model.PhonebookUser;

import java.util.Collection;


public class SimplePhonebookManager implements PhonebookManager {

    @Override
    public Collection<PhonebookEntry> entries(PhonebookUser user, String filter) {
        return null;
    }

    @Override
    public Collection<PhonebookEntry> entries(PhonebookUser user) {
        return null;
    }

    @Override
    public PhonebookEntry getEntry(Long id) {
        return null;
    }

    @Override
    public PhonebookEntry addEntry(PhonebookEntry entry) {
        return null;
    }

    @Override
    public PhonebookEntry updateEntry(PhonebookEntry entry) {
        return null;
    }

    @Override
    public Long deleteEntry(PhonebookEntry entry) {
        return null;
    }
}

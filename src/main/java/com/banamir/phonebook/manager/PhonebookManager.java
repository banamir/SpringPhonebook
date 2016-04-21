package com.banamir.phonebook.manager;

import com.banamir.phonebook.model.PhonebookEntry;
import com.banamir.phonebook.model.PhonebookUser;

import java.util.Collection;
import java.util.Map;


public interface PhonebookManager {

    public Collection<PhonebookEntry> entries(PhonebookUser user, String filter);

    public Collection<PhonebookEntry> entries(PhonebookUser user);

    public PhonebookEntry getEntry(Long id);

    public PhonebookEntry addEntry(PhonebookEntry entry);

    public PhonebookEntry updateEntry(PhonebookEntry entry);

    public Long deleteEntry(PhonebookEntry entry);
}

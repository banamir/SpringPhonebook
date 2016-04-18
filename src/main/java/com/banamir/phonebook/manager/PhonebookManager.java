package com.banamir.phonebook.manager;

import com.banamir.phonebook.model.PhonebookEntry;
import com.banamir.phonebook.model.PhonebookUser;

import java.util.Collection;
import java.util.Map;

/**
 * Created by qween on 18.04.16.
 */
public interface PhonebookManager {

    public Collection<PhonebookEntry> entries(PhonebookUser user, Map<String,String> filters);

    public PhonebookEntry addEntry(PhonebookEntry entry);

    public PhonebookEntry updateEntry(PhonebookEntry entry);

    public Long deleteEntry(PhonebookEntry entry);
}

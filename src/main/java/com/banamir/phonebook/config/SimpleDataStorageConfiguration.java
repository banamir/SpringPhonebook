package com.banamir.phonebook.config;

import com.banamir.phonebook.manager.PhonebookManager;
import com.banamir.phonebook.manager.UserManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "storage", name = "type", havingValue = "localStorage")
public class SimpleDataStorageConfiguration implements DataStorageConfiguration {

    @Override
    public PhonebookManager phonebookManager() {
        return null;
    }

    @Override
    public UserManager userManager() {
        return null;
    }
}

package com.banamir.phonebook.config;

import com.banamir.phonebook.manager.PhonebookManager;
import com.banamir.phonebook.manager.UserManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


public interface DataStorageConfiguration {



    @Bean
    PhonebookManager phonebookManager();

    @Bean
    UserManager userManager();






}

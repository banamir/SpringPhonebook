package com.banamir.phonebook.manager;

import com.banamir.phonebook.model.PhonebookUser;

/**
 * Created by qween on 18.04.16.
 */
public interface UserManager {

    public PhonebookUser getUserByUsername(String username);

    public PhonebookUser addUser(PhonebookUser user);

    public PhonebookUser updateUser(PhonebookUser user);

    public Long deleteUser(PhonebookUser user);

}

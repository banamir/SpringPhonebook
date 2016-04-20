package com.banamir.phonebook.services;

import com.banamir.phonebook.manager.UserManager;
import com.banamir.phonebook.model.PhonebookUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsManager, UserDetailsService {

    @Autowired
    private UserManager userManager;

    @Override
    public void createUser(UserDetails userDetails) {

        PhonebookUser user;

        if(userDetails instanceof  PhonebookUser)
            user = (PhonebookUser) userDetails;
        else
            user = new PhonebookUser(null,userDetails.getUsername(),
                    userDetails.getPassword(),userDetails.getUsername(),userDetails.getAuthorities());

        userManager.addUser(user);
    }

    @Override
    public void updateUser(UserDetails userDetails) {

        PhonebookUser user = null;

        if(userDetails instanceof  PhonebookUser)
            user = (PhonebookUser) userDetails;
        else
            throw new IllegalStateException("updating user must be instance of PhonebookUser");

        userManager.updateUser(user);
    }

    @Override
    public void deleteUser(String username) {

        PhonebookUser user = userManager.getUserByUsername(username);

        if(user != null) userManager.deleteUser(user);

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();

        if (currentUser == null) {
            // This would indicate bad coding somewhere
            throw new AccessDeniedException("Can't change password as no Authentication object found in context " +
                    "for current user.");
        }

        String username = currentUser.getName();

        PhonebookUser user =  userManager.getUserByUsername(username);

        if(user == null)
            throw new AccessDeniedException("The current user not founded in the system");

        if(user.getPassword() != oldPassword)
            throw new AccessDeniedException("The old password not matches whith the stored in the system");

        user = userManager.updateUser(new PhonebookUser(user.getId(),user.getUsername(),newPassword,user.getFullName(),user.getAuthorities()));

        UsernamePasswordAuthenticationToken newAuthentication =
                new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
        newAuthentication.setDetails(currentUser.getDetails());

        SecurityContextHolder.getContext().setAuthentication(newAuthentication);
    }

    @Override
    public boolean userExists(String username) {

        return userManager.getUserByUsername(username) != null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        PhonebookUser user = userManager.getUserByUsername(username);

        if(user == null) throw new UsernameNotFoundException("Can't find the user");

        return user;
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

}

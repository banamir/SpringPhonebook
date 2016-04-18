package com.banamir.phonebook.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


public class PhonebookUser implements UserDetails {

    private Long id;

    private String password;

    private String username;

    private String fullName;

    private Collection<? extends  GrantedAuthority> authorities;




    public PhonebookUser(Long id, String username,
                         String password,
                         String fullName,
                         Collection<? extends  GrantedAuthority> authorities){
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

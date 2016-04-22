package com.banamir.phonebook.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;


@Entity
public class PhonebookUser implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;

    @Size(min = 3)
    @NotNull
    private String password;

    @Size(min = 3)
    @NotNull
    @Pattern(regexp = "^(\\w+)$", message = "Wrong format of phone number")
    private String username;

    @Size(min = 5)
    private String fullName;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private Collection<PhonebookEntry> entries;

    private Collection<? extends  GrantedAuthority> authorities;



    public PhonebookUser(String username,
                         String password,
                         String fullName,
                         Collection<? extends  GrantedAuthority> authorities){
        this(null,username,password,fullName,authorities);
    }

    public PhonebookUser(Long id, String username,
                         String password,
                         String fullName,
                         Collection<? extends  GrantedAuthority> authorities){

        if(authorities == null)
            throw new IllegalArgumentException("The authorities can't be null");

        this.id = id;
        this.username = username;
        this.password =  password;
        this.fullName = fullName;
        this.authorities = authorities;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhonebookUser)) return false;

        PhonebookUser that = (PhonebookUser) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (!getPassword().equals(that.getPassword())) return false;
        if (!getUsername().equals(that.getUsername())) return false;
        if (!getFullName().equals(that.getFullName())) return false;
        return !(getAuthorities() != null ? !getAuthorities().equals(that.getAuthorities()) : that.getAuthorities() != null);

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + getPassword().hashCode();
        result = 31 * result + getUsername().hashCode();
        result = 31 * result + getFullName().hashCode();
        result = 31 * result + (getAuthorities() != null ? getAuthorities().hashCode() : 0);
        return result;
    }

}

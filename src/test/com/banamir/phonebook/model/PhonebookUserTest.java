package com.banamir.phonebook.model;

import com.banamir.phonebook.AbstractTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;



public class PhonebookUserTest extends AbstractTest {

    static public final List<GrantedAuthority> USER_AUTHORITY = new ArrayList<>();

    static {
        USER_AUTHORITY.add(new SimpleGrantedAuthority("USER"));
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void idValueTest(){

        //Checking two forms of cunstructor with and without setting id of user

        PhonebookUser u1 = new PhonebookUser("user","password","User fullname",USER_AUTHORITY);
        PhonebookUser u2 = new PhonebookUser(1L,"user","password","User fullname",USER_AUTHORITY);

        assertNull(u1.getId());
        assertEquals(1L, u2.getId().longValue());
    }

    @Test
    public void getAuthoritiesTest() {

        //Checking if authorities equals to  null then constructor throws exception

        PhonebookUser u1 = new PhonebookUser("user","password","User fullname",USER_AUTHORITY);
        assertEquals(USER_AUTHORITY,u1.getAuthorities());

        try{
            PhonebookUser u2 = new PhonebookUser("user","password","User fullname",null);
            fail();
        } catch(IllegalArgumentException e) { }
    }

    @Test
    public void mainGetterTest() {

        //Checking of getters for username, password and fullname fields

        PhonebookUser u = new PhonebookUser(1L, "user","password","User fullname",USER_AUTHORITY);
        assertEquals("user",u.getUsername());
        assertEquals("password",u.getPassword());
        assertEquals("User fullname",u.getFullName());
    }

    @Test
    public void notSupportedFieldsTest(){

        //In this implementation  enabled, credentialsNonExpired, accountNonLocked and AccountNonExpired
        //fields aren't used and have default value true.

        PhonebookUser u = new PhonebookUser(1L, "user","password","User fullname",USER_AUTHORITY);
        assertEquals(true,u.isEnabled());
        assertEquals(true,u.isCredentialsNonExpired());
        assertEquals(true,u.isAccountNonLocked());
        assertEquals(true,u.isAccountNonExpired());
    }



}
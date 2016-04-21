package com.banamir.phonebook.services;

import com.banamir.phonebook.manager.UserManager;
import com.banamir.phonebook.model.PhonebookUser;
import com.banamir.phonebook.model.PhonebookUserTest;
import org.junit.Before;
import org.junit.Test;

import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class UserServiceTest extends PhonebookUserTest {

    public final static UserDetails
        u1 = new PhonebookUser(1L,"1st_user","password", "Full name", USER_AUTHORITY),
        u2 = new User("2nd_user","password", USER_AUTHORITY),
        u3 = new PhonebookUser(2L,"3rd_user","x","Full Name", USER_AUTHORITY);

    @Mock
    UserManager userManager;

    private UserService userService = new UserService();

    @Before
    public void setUp() throws Exception {
        super.setUp();
        userService.setUserManager(userManager);
    }

    @Test
    public void testCreateUser() throws Exception {

        doThrow(new IllegalStateException()).when(userManager).addUser((PhonebookUser) u3);

        userService.createUser(u1);
        userService.createUser(u2);
        try {
            userService.createUser(u3);
            fail();
        } catch (IllegalStateException e) { }

        verify(userManager,times(1)).addUser((PhonebookUser) u1);
        verify(userManager,times(3)).addUser(any(PhonebookUser.class));
    }

    @Test
    public void testUpdateUser() throws Exception {

        doThrow(new IllegalStateException()).when(userManager).updateUser((PhonebookUser) u3);

        userService.updateUser(u1);
        try {
            userService.updateUser(u2);
            fail();
        } catch (IllegalStateException e) { }
        try {
            userService.updateUser(u3);
            fail();
        } catch (IllegalStateException e) { }


        verify(userManager,times(2)).updateUser(any(PhonebookUser.class));

    }

    @Test
    public void testDeleteUser() throws Exception {

        when(userManager.getUserByUsername("1st_user")).thenReturn((PhonebookUser) u1);

        userService.deleteUser("1st_user");
        userService.deleteUser("2nd_user");

        verify(userManager,times(1)).deleteUser(any(PhonebookUser.class));
    }

    @Test
    public void testChangePassword() throws Exception {

        Authentication auth =
                new UsernamePasswordAuthenticationToken(u1,u1.getPassword());
        Authentication badAuth =
                new UsernamePasswordAuthenticationToken(u2,u2.getPassword());


        when(userManager.getUserByUsername("1st_user")).thenReturn((PhonebookUser) u1);
        when(userManager.updateUser(any(PhonebookUser.class))).thenAnswer(new Answer<PhonebookUser>() {
            @Override
            public PhonebookUser answer(InvocationOnMock invockation) {
                return (PhonebookUser) invockation.getArguments()[0];
            }
        });

        try{
            userService.changePassword("password","new password");
            fail();
        } catch(AccessDeniedException e) { }

        SecurityContextHolder.getContext().setAuthentication(badAuth);

        try{
            userService.changePassword("password","new password");
            fail();
        } catch(AccessDeniedException e) { }

        SecurityContextHolder.getContext().setAuthentication(auth);

        try{
            userService.changePassword("bad password", "new password");
            fail();
        } catch(AccessDeniedException e) { }


        userService.changePassword("password", "new password");

        auth = SecurityContextHolder.getContext().getAuthentication();
        assertEquals(auth.getCredentials(), "new password");
        assertEquals(((UserDetails)auth.getPrincipal()).getPassword(), "new password");
    }

    @Test
    public void testUserExists() throws Exception {

        when(userManager.getUserByUsername("1st_user")).thenReturn((PhonebookUser) u1);

        assertTrue(userService.userExists("1st_user"));
        assertFalse(userService.userExists("2nd_user"));

    }

    @Test
    public void testLoadUserByUsername() throws Exception {

        when(userManager.getUserByUsername("1st_user")).thenReturn((PhonebookUser) u1);

        assertEquals(u1,userService.loadUserByUsername("1st_user"));
        try{
            userService.loadUserByUsername("2nd_user");
            fail();
        } catch (UsernameNotFoundException e) { }

    }
}
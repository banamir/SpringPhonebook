package com.banamir.phonebook.services;

import com.banamir.phonebook.AbstractTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static com.banamir.phonebook.services.UserServiceTest.*;


public class SecurityServiceTest extends AbstractTest {


    @Mock
    AuthenticationManager authenticationManager;

    @Mock
    UserService userService;


    SecurityService service = new SecurityService();



    @Before
    public void setUp() throws Exception {
        super.setUp();

        service.setAuthenticationManager(authenticationManager);
        service.setUserService(userService);

        when(userService.loadUserByUsername("1st_user")).thenReturn(u1);
        doThrow(new UsernameNotFoundException("")).when(userService).loadUserByUsername("2nd_user");
        when(userService.loadUserByUsername("3rd_user")).thenReturn(u3);
    }

    @Test
    public void testGetCurrentUser() throws Exception {

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(u1, u1.getPassword()));
        assertEquals(u1, service.getCurrentUser());
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(u2, u2.getPassword()));
        try{
            assertEquals(u2, service.getCurrentUser());
            fail();
        } catch (UsernameNotFoundException e) { }
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(u3, u3.getPassword()));
        assertEquals(u3, service.getCurrentUser());

    }

    @Test
    public void testAutologin() throws Exception {

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenAnswer(new Answer<Authentication>() {
            @Override
            public Authentication answer(InvocationOnMock invocationOnMock) throws Throwable {
                UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) invocationOnMock.getArguments()[0];
                if (auth.getPrincipal() instanceof UserDetails) {
                    UserDetails details = (UserDetails) auth.getPrincipal();
                    if (details == u1 && details.getPassword().equals(auth.getCredentials()))
                        auth = new UsernamePasswordAuthenticationToken(details, details.getPassword(), null);
                    else
                        auth.setAuthenticated(false);
                } else {
                    auth.setAuthenticated(false);
                }
                return auth;
            }
        });

        service.autologin(u1.getUsername(), u1.getPassword());
        assertTrue(SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
        SecurityContextHolder.getContext().setAuthentication(null);

        service.autologin(u1.getUsername(), "badpassword");
        assertNull(SecurityContextHolder.getContext().getAuthentication());
        SecurityContextHolder.getContext().setAuthentication(null);

        try{
            service.autologin(u2.getUsername(),u2.getPassword());
            fail();
        } catch (UsernameNotFoundException e) { }


        service.autologin(u3.getUsername(),u3.getPassword());
        assertNull(SecurityContextHolder.getContext().getAuthentication());


    }
}
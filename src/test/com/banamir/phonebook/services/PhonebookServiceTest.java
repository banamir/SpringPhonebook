package com.banamir.phonebook.services;

import com.banamir.phonebook.AbstractTest;
import com.banamir.phonebook.manager.PhonebookManager;
import com.banamir.phonebook.model.PhonebookEntry;
import com.banamir.phonebook.model.PhonebookUser;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static  com.banamir.phonebook.services.UserServiceTest.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PhonebookServiceTest extends AbstractTest {


    public static PhonebookEntry
            e1 = new PhonebookEntry(),
            e2 = new PhonebookEntry(),
            e3 = new PhonebookEntry(),
            e4 = new PhonebookEntry(),
            e5 = new PhonebookEntry(),
            e6 = new PhonebookEntry();

    public static List<PhonebookEntry> u1EntryList = new ArrayList<>();
    public static List<PhonebookEntry> u3EntryList = new ArrayList<>();

    static {

        e1.setId(1L).setFirstName("Name1").setMiddleName("MiddleName1").setLastName("LastName1")
              .setUser((PhonebookUser) u1).setHomePhone("+38(00)000-00-00");
        e2.setId(2L).setFirstName("Name2").setMiddleName("MiddleName2").setLastName("LastName2")
                .setUser((PhonebookUser) u1).setHomePhone("+38(00)000-00-00");
        e3.setId(3L).setFirstName("Name3").setMiddleName("MiddleName3").setLastName("LastName3")
                .setUser((PhonebookUser) u1).setHomePhone("+38(00)000-00-00");
        e4.setId(4L).setFirstName("Name4").setMiddleName("MiddleName4").setLastName("LastName4")
                .setUser((PhonebookUser) u3).setHomePhone("+38(00)000-00-00");
        e5.setId(5L).setFirstName("Name5").setMiddleName("MiddleName5").setLastName("LastName5")
                .setUser((PhonebookUser) u3).setHomePhone("+38(00)000-00-00");
        e6.setId(6L).setFirstName("Name6").setMiddleName("MiddleName6").setLastName("LastName6")
                .setUser((PhonebookUser) u3).setHomePhone("+38(00)000-00-00");

        u1EntryList.addAll(Arrays.asList(new PhonebookEntry[]{e1, e2, e3}));
        u3EntryList.addAll(Arrays.asList(new PhonebookEntry[]{e4,e5,e6}));

    }

    @Mock
    private PhonebookManager phonebookManager;

    @Mock
    private UserService userService;

    private PhonebookService phonebookService = new PhonebookService();


    @Before
    public void setUp() throws Exception {
        super.setUp();

        phonebookService.setManager(phonebookManager);
        phonebookService.setUserService(userService);

        //Mock objects
        when(phonebookManager.entries((PhonebookUser) u1, null)).thenReturn(u1EntryList);
        when(phonebookManager.entries((PhonebookUser) u3, null)).thenReturn(u3EntryList);

        when(userService.loadUserByUsername("1st_user")).thenReturn(u1);
        doThrow(new UsernameNotFoundException("")).when(userService).loadUserByUsername("2nd_user");
        when(userService.loadUserByUsername("3rd_user")).thenReturn(u3);
    }

    @Test
    public void testList() throws Exception {

        //user isn't authenticated
        SecurityContextHolder.getContext().setAuthentication(null);
        try{
            phonebookService.list(null);
            fail();
        } catch (AccessDeniedException e) { }

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(u2, u2.getPassword()));
        try{
            phonebookService.list(null);
            fail();
        } catch (AccessDeniedException e) { }

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(u1, u1.getPassword()));
        assertEquals(u1EntryList, phonebookService.list(null));

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(u3, u3.getPassword()));
        assertEquals(u3EntryList,phonebookService.list(null));
    }

    @Test
    public void testCreate() throws Exception {

        PhonebookEntry entry = new PhonebookEntry().setFirstName("Name").setMiddleName("MiddleName").setLastName("LastName")
                .setAddress("Address").setEmail("email@host.com").setMobilePhone("+38(00)123-45-67").setHomePhone("+38(00)765-43-21");

        when(phonebookManager.addEntry(any(PhonebookEntry.class))).thenAnswer(new Answer<PhonebookEntry>() {
            @Override
            public PhonebookEntry answer(InvocationOnMock invocationOnMock) throws Throwable {
                PhonebookEntry entry = (PhonebookEntry) invocationOnMock.getArguments()[0];
                entry.setId(1L);
                entry.setUser((PhonebookUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
                u1EntryList.add(entry);
                return entry;
            }
        });

        //user isn't authenticated
        SecurityContextHolder.getContext().setAuthentication(null);
        try {
            phonebookService.create(entry);
        } catch (AccessDeniedException e) { }
        //not valid user
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(u2, u2.getPassword()));
        try{
            phonebookService.create(entry);
            fail();
        } catch (AccessDeniedException e) { }

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(u1, u1.getPassword()));
        PhonebookEntry savedEntry = phonebookService.create(entry);

        verify(phonebookManager,times(1)).addEntry(any(PhonebookEntry.class));

        assertEquals(((PhonebookUser)u1).getId(), savedEntry.getUserId());

    }

    @Test
    public void testUpdate() throws Exception {

        when(phonebookManager.getEntry(1L)).thenReturn(e1);
        when(phonebookManager.updateEntry(any(PhonebookEntry.class))).thenAnswer(new Answer<PhonebookEntry>() {
            @Override
            public PhonebookEntry answer(InvocationOnMock invocationOnMock) throws Throwable {
                return (PhonebookEntry) invocationOnMock.getArguments()[0];
            }
        });

        //user isn't authenticated
        SecurityContextHolder.getContext().setAuthentication(null);
        try {
            phonebookService.update(e1);
            fail();
        } catch (AccessDeniedException e) { }
        //not valid user
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(u2, u2.getPassword()));
        try{
            phonebookService.update(e1);
            fail();
        } catch (AccessDeniedException e) { }
        //updated entry belongs to another user
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(u3, u3.getPassword()));
        try{
            phonebookService.update(e1);
            fail();
        } catch (AccessDeniedException e) { }

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(u1, u1.getPassword()));
        phonebookService.update(e1);

        verify(phonebookManager,times(1)).updateEntry(any(PhonebookEntry.class));
    }

    @Test
    public void testDelete() throws Exception {

        when(phonebookManager.getEntry(1L)).thenReturn(e1);
        when(phonebookManager.deleteEntry(any(PhonebookEntry.class))).thenAnswer(new Answer<Long>() {
            @Override
            public Long answer(InvocationOnMock invocationOnMock) throws Throwable {
                return ((PhonebookEntry) invocationOnMock.getArguments()[0]).getId();
            }
        });

        //user isn't authenticated
        SecurityContextHolder.getContext().setAuthentication(null);
        try {
            phonebookService.delete(e1);
            fail();
        } catch (AccessDeniedException e) { }
        //not valid user
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(u2, u2.getPassword()));
        try{
            phonebookService.delete(e1);
            fail();
        } catch (AccessDeniedException e) { }
        //deleted entry belongs to another user
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(u3, u3.getPassword()));
        try{
            phonebookService.delete(e1);
            fail();
        } catch (AccessDeniedException e) { }

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(u1, u1.getPassword()));
        phonebookService.delete(e1);

        verify(phonebookManager,times(1)).deleteEntry(any(PhonebookEntry.class));
    }

}
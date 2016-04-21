package com.banamir.phonebook.controller;

import com.banamir.phonebook.AbstractTest;
import com.banamir.phonebook.model.JsonMessage;
import com.banamir.phonebook.model.PhonebookEntry;
import com.banamir.phonebook.services.PhonebookService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static com.banamir.phonebook.services.PhonebookServiceTest.*;
import static  com.banamir.phonebook.services.UserServiceTest.*;


public class PhonebookEntryControllerTest extends AbstractTest {



    @Mock
    PhonebookService service;


    PhonebookEntryController controller = new PhonebookEntryController();

    @Before
    public void setUp() throws Exception {
        super.setUp();

        controller.setService(service);
    }

    @Test
    public void testGet() throws Exception {

        when(service.list(null)).thenAnswer(new Answer<Collection<PhonebookEntry>>() {
            @Override
            public Collection<PhonebookEntry> answer(InvocationOnMock invocationOnMock) throws Throwable {
                if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() == u1)
                    return u1EntryList;
                if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() == u3)
                    return u3EntryList;
                return null;
            }
        });

        JsonMessage result;

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(u1, u1.getPassword()));
        result = controller.get(null);
        assertEquals(0L, result.getStatus().longValue());
        assertEquals(u1EntryList,result.getData());

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(u3, u3.getPassword()));
        result = controller.get(null);
        assertEquals(0L, result.getStatus().longValue());
        assertEquals(u3EntryList, result.getData());

        doThrow(new AccessDeniedException("")).when(service).list(null);
        try{
            controller.get(null);
            fail();
        } catch (Exception e) {}
    }


    @Test
    public void testAdd() throws Exception {

        when(service.create(any(PhonebookEntry.class))).thenReturn(e1);

        JsonMessage result;

        BindingResult binding = mock(BindingResult.class);
        when(binding.hasErrors()).thenReturn(false);

        result = controller.add(e1,binding);
        assertEquals(0L, result.getStatus().longValue());
        assertEquals(e1, result.getData());

        doThrow(new AccessDeniedException("")).when(service).create(any(PhonebookEntry.class));
        try{
            controller.add(e1,binding);
            fail();
        } catch (AccessDeniedException e) { }

        when(binding.hasErrors()).thenReturn(true);

        List<ObjectError> errorlist = new ArrayList<>();
        errorlist.add(new ObjectError("PhonebookEntry", "validationMessage"));
        when(binding.getAllErrors()).thenReturn(errorlist);
        try{
            controller.add(e1, binding);
            fail();
        } catch (IllegalStateException e) { }

    }

    @Test
    public void testUpdate() throws Exception {

        when(service.update(any(PhonebookEntry.class))).thenReturn(e1);

        JsonMessage result;

        BindingResult binding = mock(BindingResult.class);
        when(binding.hasErrors()).thenReturn(false);

        result = controller.update(e1,binding);
        assertEquals(0L, result.getStatus().longValue());
        assertEquals(e1, result.getData());

        doThrow(new AccessDeniedException("")).when(service).update(any(PhonebookEntry.class));
        try{
            controller.update(e1, binding);
            fail();
        } catch (AccessDeniedException e) { }

        when(binding.hasErrors()).thenReturn(true);
        when(binding.getAllErrors()).thenReturn(
                Arrays.asList(new ObjectError[]{new ObjectError("PhonebookEntry", "validationMessage")}));
        try{
            controller.update(e1, binding);
            fail();
        } catch (IllegalStateException e) { }

    }

    @Test
    public void testDelete() throws Exception {

        when(service.delete(any(PhonebookEntry.class))).thenReturn(1L);

        JsonMessage result;

        BindingResult binding = mock(BindingResult.class);
        when(binding.hasErrors()).thenReturn(false);

        result = controller.delete(e1,binding);
        assertEquals(0L, result.getStatus().longValue());
        assertEquals(1L, result.getData());

        doThrow(new AccessDeniedException("")).when(service).delete(any(PhonebookEntry.class));
        try{
            controller.delete(e1, binding);
            fail();
        } catch (AccessDeniedException e) { }

        when(binding.hasErrors()).thenReturn(true);

        List<ObjectError> errorlist = new ArrayList<>();
        errorlist.add(new ObjectError("PhonebookEntry", "validationMessage"));
        when(binding.getAllErrors()).thenReturn(errorlist);
        try{
            controller.delete(e1, binding);
            fail();
        } catch (IllegalStateException e) { }

    }

    @Test
    public void testErrorHandler() throws Exception {

        Exception ex = new IllegalStateException("illegal state exception");

        JsonMessage result  = controller.errorHandler(ex);

        assertEquals(1L,result.getStatus().longValue());
        assertEquals(ex.getMessage(),result.getData());
    }
}
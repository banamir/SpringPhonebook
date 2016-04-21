package com.banamir.phonebook.controller;

import com.banamir.phonebook.AbstractTest;
import com.banamir.phonebook.model.PhonebookUser;
import com.banamir.phonebook.services.SecurityService;
import com.banamir.phonebook.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.Arrays;

import static org.mockito.Mockito.*;

import static com.banamir.phonebook.services.UserServiceTest.*;

public class MainControllerTest extends AbstractTest {

    @Mock
    private UserService userService;

    @Mock
    private SecurityService securityService;


    MainController mainController = new MainController();

    @Before
    public void setUp() throws Exception {
        super.setUp();

        mainController.setSecurityService(securityService);
        mainController.setUserService(userService);

    }

    @Test
    public void testLogin() throws Exception {

        Model model;

        model = new ExtendedModelMap();
        mainController.login(null,null,model);
        assertFalse(model.containsAttribute("error"));
        assertFalse(model.containsAttribute("message"));

        model = new ExtendedModelMap();
        mainController.login("", null, model);
        assertTrue(model.containsAttribute("error"));
        assertFalse(model.containsAttribute("message"));

        model = new ExtendedModelMap();
        mainController.login("", "", model);
        assertTrue(model.containsAttribute("error"));
        assertTrue(model.containsAttribute("message"));

    }

    @Test
    public void testRegistration() throws Exception {

        Model model;
        String result;

        BindingResult binding = mock(BindingResult.class);
        when(binding.hasErrors()).thenReturn(false);

        model = new ExtendedModelMap();
        when(binding.hasErrors()).thenReturn(true);
        when(binding.getAllErrors()).thenReturn(
                Arrays.asList(new ObjectError[]{new ObjectError("PhonebookEntry", "validationMessage")}));
        result = mainController.registration((PhonebookUser) u3, binding,model);
        assertEquals("registration", result);
        assertTrue(model.containsAttribute("user"));
        assertEquals(u3, model.asMap().get("user"));
        assertTrue(model.containsAttribute("error"));
        assertEquals("validationMessage", model.asMap().get("error"));

        when(binding.hasErrors()).thenReturn(false);
        doThrow(new IllegalStateException("Some illegal state error")).when(userService).createUser(u3);
        result = mainController.registration((PhonebookUser) u3, binding,model);
        assertEquals("registration", result);
        assertTrue(model.containsAttribute("user"));
        assertEquals(u3, model.asMap().get("user"));
        assertTrue(model.containsAttribute("error"));
        assertEquals("Some illegal state error", model.asMap().get("error"));

        result = mainController.registration((PhonebookUser) u1, binding,model);
        assertEquals("redirect:/phonebook", result);
    }

    @Test
    public void testPhonebook() throws Exception {

        when(securityService.getCurrentUser()).thenReturn((PhonebookUser) u1);

        Model model = new ExtendedModelMap();

        assertEquals("phonebook",mainController.phonebook(model));
        assertTrue(model.containsAttribute("user"));
        assertEquals(u1, model.asMap().get("user"));

    }
}
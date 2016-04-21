package com.banamir.phonebook.controller;

import com.banamir.phonebook.model.JsonMessage;
import com.banamir.phonebook.model.PhonebookEntry;
import com.banamir.phonebook.services.PhonebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.naming.Binding;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("phonebook/entry")
public class PhonebookEntryController {

    @Autowired
    private PhonebookService service;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
     JsonMessage get(@RequestParam String filter){

        return new JsonMessage(0L,service.list(filter)) ;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    JsonMessage add(@Valid PhonebookEntry entry, BindingResult binding){

        if (binding.hasErrors()) {
            throw new IllegalStateException(binding.getAllErrors().get(0).getCode());
        }

        return new JsonMessage(0L, service.create(entry));
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    JsonMessage update(@Valid PhonebookEntry entry, BindingResult binding){

        if (binding.hasErrors()) {
            throw new IllegalStateException(binding.getAllErrors().get(0).getCode());
        }

        return new JsonMessage(0L, service.update(entry));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    JsonMessage delete(@Valid PhonebookEntry entry, BindingResult binding){

        if (binding.hasErrors()) {
            throw new IllegalStateException(binding.getAllErrors().get(0).getCode());
        }

        return new JsonMessage(0L, service.delete(entry));
    }

    @ExceptionHandler(Exception.class)
    JsonMessage errorHandler(Exception exception){

        return new JsonMessage(1L, exception.getMessage());
    }

    public void setService(PhonebookService service) {
        this.service = service;
    }
}

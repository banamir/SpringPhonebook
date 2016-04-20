package com.banamir.phonebook.controller;

import com.banamir.phonebook.model.PhonebookEntry;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.naming.Binding;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/entry")
public class PhonebookEntryController {

    @RequestMapping(value = "/getEntry", method = RequestMethod.GET)
    List<PhonebookEntry> get(@RequestParam Map<String,String> filters){
        //TODO: implement this
        return null;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    PhonebookEntry add(@Valid PhonebookEntry entry){
        //TODO: implement this
        return null;
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    PhonebookEntry update(@Valid PhonebookEntry entry, BindingResult binding){
        //TODO: implement this
        return null;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    Long delete(@Valid PhonebookEntry entry){
        //TODO: implement this
        return null;
    }

}

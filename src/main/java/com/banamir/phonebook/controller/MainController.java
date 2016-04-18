package com.banamir.phonebook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {


    @RequestMapping("/login")
    public String login(){
        //TODO: must be implemented
        return "login";
    }

    @RequestMapping("/registration")
    public String registration(){
        //TODO: must be implemented
        return "registration";
    }

    @RequestMapping("/phonebook")
    public String phonebook(){
        return "phonebook";
    }
}

package com.banamir.phonebook.controller;

import com.banamir.phonebook.model.PhonebookUser;
import com.banamir.phonebook.services.SecurityService;
import com.banamir.phonebook.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class MainController {


    @Autowired
    UserService userService;

    @Autowired
    SecurityService securityService;

    @RequestMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "message", required = false) String message,
                        Model model){

        if(error != null)
            model.addAttribute("error","Invalid username or password");
        if(error != null)
            model.addAttribute("message",message);

        return "login";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@Valid @ModelAttribute("userForm") PhonebookUser user,  BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()) {
            model.addAttribute("error",bindingResult.getAllErrors().get(0).toString());
            model.addAttribute("user",user);
            return "registration";
        }

        try {
            userService.createUser(user);
            securityService.autologin(user.getUsername(),user.getPassword());

            return "redirect:/phonebook";

        } catch (Exception e){
            model.addAttribute("error",bindingResult.getAllErrors().get(0).toString());
            return "registration";
        }


    }

    @RequestMapping("/phonebook")
    public String phonebook(Model model){

        model.addAttribute("user",securityService.getCurrentUser());

        return "phonebook";
    }
}

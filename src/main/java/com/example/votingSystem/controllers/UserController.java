package com.example.votingSystem.controllers;

import com.example.votingSystem.models.User;
import com.example.votingSystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String viewStartPage() {
        return "index";
    }

    @GetMapping("/index")
    public String viewHomePage() {
        return "index";
    }


    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "signUp";
    }

    @PostMapping("/register")
    public String processRegister(@ModelAttribute("user") User curr){

        if(userService.presentUser(curr.getPIN())){
            return "redirect:/alreadyVoted";
        }
        if(!userService.isAdult(curr.getPIN())){
            return "redirect:/underAge";
        }
        userService.createUser(curr.getPIN());
        return "redirect:/login";

    }

    @GetMapping("/alreadyVoted")
    public String alreadyVoted() {
        return "alreadyVoted";
    }

    @GetMapping("/underAge")
    public String under_age() {
        return "underAge";
    }

}

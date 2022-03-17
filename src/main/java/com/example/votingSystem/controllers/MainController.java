package com.example.votingSystem.controllers;

import com.example.votingSystem.models.User;
import com.example.votingSystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class MainController {


    @Autowired
    private UserRepository userRepo;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @GetMapping("")
    public String viewStartPage() {
        return "index";
    }

    @GetMapping("/index")
    public String viewHomePage() {
        return "index";
    }
/*
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "sign_up";
    }
    @PostMapping("/register")
    public String processRegister(User user) {
        int born_year = Integer.parseInt(user.getPIN().substring(0, 2));
        LocalDate now = LocalDate.now();
        int local_year = now.getYear() % 100;
        if(ageParser(user.getPIN()).equals("20")){
            user.setAge(100 - born_year + local_year);
        }else if(ageParser(user.getPIN()).equals("21")){
            user.setAge(local_year - born_year);
        }else{
            //TODO: wrong PIN;
        }

        if(user.getAge() < 18){
            return "redirect:/under-age";
        }
        if(presentUser(user.getPIN())){
            return "redirect:/alreadyVoted";
        }

        user.setPIN(encoder.encode(user.getPIN()));
        userRepo.save(user);
        return "redirect:/main";

    }

    @GetMapping("/main")
    public String voting_main() {
        return "main";
    }

    @GetMapping("/alreadyVoted")
    public String alreadyVoted() {
        return "alreadyVoted";
    }

    @GetMapping("/under-age")
    public String under_age() {
        return "underAge";
    }

    private String ageParser(String date){
        String months = date.substring(2, 4);
        if(months.startsWith("4") || months.startsWith("5")){
            return "21";
        }else if(months.startsWith("0") || months.startsWith("1")){
            return "20";
        }
        return "wrong";
    }

    private boolean presentUser(String pin){
        List<String> pins = new ArrayList<>();
        for(int i = 0; i < userRepo.findAll().size(); i++){
            pins.add(userRepo.findAll().get(i).getPIN());
        }
        for (String s : pins) {
            if (encoder.matches(pin, s)) {
                return true;
            }
        }
        return false;
    }
*/


}

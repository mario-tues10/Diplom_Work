package com.example.votingSystem.services;


import com.example.votingSystem.models.Election;
import com.example.votingSystem.models.User;
import com.example.votingSystem.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.regex.Pattern;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void createUser(String pin){
        User curr = new User();
        curr.setPIN(pin);
        curr.setBirth(createDate(pin));
        userRepository.save(curr);
    }


    public boolean isAdult(String pin){
        LocalDate curr = LocalDate.now();
        LocalDate userYears = createDate(pin);
        long diff = ChronoUnit.YEARS.between(userYears, curr);

        return diff >= 18;
    }

    public boolean presentUser(String pin){
        return userRepository.findByPIN(pin) != null;
    }
    
    private LocalDate createDate(String pin){
        String bornYear = pin.substring(0, 2);
        String bornMonth = pin.substring(2, 4);
        
        String bornDate = pin.substring(4, 6);
        if(ageParser(bornMonth).equals("20")){
            bornYear = "19" + bornYear;
        }else if(ageParser(bornMonth).equals("21")){
            bornYear = "20" + bornYear;
            bornMonth = String.valueOf((Integer.parseInt(bornMonth) - 40));
        }

        if(Pattern.matches("[1-9]", bornMonth)){
            bornMonth = "0" + bornMonth;
        }
         
        String exactDate = bornDate + "-" + bornMonth + "-" + bornYear;
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(exactDate, dateFormat);
    }

    private String ageParser(String months){
        if(months.startsWith("4") || months.startsWith("5")){
            return "21";
        }else if(months.startsWith("0") || months.startsWith("1")){
            return "20";
        }
        return "wrong";
    }

    public User getUser(String pin){
        return userRepository.findByPIN(pin);
    }

    public boolean isUserVotedForElection(User user, Election election){
        return user.getElections().contains(election);
    }

    @Transactional
    public void saveUser(User user){
        userRepository.save(user);
    }
}

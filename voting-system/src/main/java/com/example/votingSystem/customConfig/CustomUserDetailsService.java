package com.example.votingSystem.customConfig;

import com.example.votingSystem.models.User;
import com.example.votingSystem.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String PIN) throws UsernameNotFoundException {
        User curr = userRepo.findByPIN(PIN);
        if (curr == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(curr);
    }
}

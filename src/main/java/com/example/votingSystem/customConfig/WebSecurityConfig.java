package com.example.votingSystem.customConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        /*        .antMatchers("/main").permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/index").permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/register").permitAll()
                .and()

         */
                .antMatchers("/voter/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .usernameParameter("PIN")
                .defaultSuccessUrl("/elections")
                .permitAll()
                .and()
                .logout().logoutSuccessUrl("/login").permitAll();
        http.headers().frameOptions().disable();
        http.csrf().disable();
    }

}

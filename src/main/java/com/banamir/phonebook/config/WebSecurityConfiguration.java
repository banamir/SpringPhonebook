package com.banamir.phonebook.config;

import com.banamir.phonebook.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

    }

    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .anyRequest().authenticated()
                .antMatchers("/public/**").permitAll()
                .antMatchers("/static/**").permitAll()
                .antMatchers("/registration").permitAll()
                .and()
            .formLogin()
                .loginPage("/login")
                .usernameParameter("j_username").passwordParameter("j_password")
                .failureUrl("/login?error")
                .defaultSuccessUrl("/phonebook")
                .loginProcessingUrl("/static/j_spring_security_check")
                .permitAll()
                .and()
            .logout()
                .logoutUrl("/static/logout")
                .logoutSuccessUrl("/logout");

    }
}

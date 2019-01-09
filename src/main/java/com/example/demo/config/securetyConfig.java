package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class securetyConfig extends WebSecurityConfigurerAdapter {

     @Override
         protected void configure(AuthenticationManagerBuilder auth) throws Exception {
             auth.inMemoryAuthentication().withUser("user").password("{noop}pass").roles("ADMIN");
             auth.inMemoryAuthentication().withUser("user1").password("{noop}pass").roles("USER");
         }

@Override
protected void configure(HttpSecurity http) throws Exception {
    http
            .authorizeRequests()
            .antMatchers("/", "/home","/login","/upl").permitAll()
            .anyRequest().authenticated()
            .antMatchers("/admin/**").hasRole("ADMIN")
            .and()
            .formLogin()
            .loginPage("/login")
            .successForwardUrl("/successURL")//handle with post mapping in controller
            .failureUrl("/login?error").permitAll()
            .permitAll()
            .and()
            .logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).
            logoutSuccessUrl("/login")
            .permitAll();
}
}

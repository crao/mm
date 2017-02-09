package com.my.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
      
    	// http.authorizeRequests().anyRequest().authenticated();
    	/* 
    	 http.authorizeRequests()
         .antMatchers("/", "/home").permitAll()
         .anyRequest().authenticated()
         .and()
         .formLogin().failureUrl("/home?error")
         .defaultSuccessUrl("/")
         .loginPage("/home")
         .permitAll()
         .and()
         .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
         .permitAll();*/
    	
    /*	http
            .authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .permitAll();*/
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	  auth
            .inMemoryAuthentication()
                .withUser("user@gmail.com").password("password").roles("USER");
    }
}
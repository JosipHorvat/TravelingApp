package com.josip.travelagency.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth
                .inMemoryAuthentication()
                .withUser("Josip").password(passwordEncoder().encode("admin")).roles("ADMIN, EMPLOYEE")
                .and()
                .withUser("Mile").password(passwordEncoder().encode("employee")).roles("EMPLOYEE")
                .and()
                .withUser("Pavo").password(passwordEncoder().encode("client")).roles("CLIENT");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.authorizeRequests()
                   .antMatchers("/", "/login")
                   .permitAll()
               .and()
                   .formLogin()
                   .defaultSuccessUrl("/")
                   .permitAll()
               .and()
                   .logout()
                   .logoutSuccessUrl("/")
                   .invalidateHttpSession(true)
                   .permitAll();
    }
}

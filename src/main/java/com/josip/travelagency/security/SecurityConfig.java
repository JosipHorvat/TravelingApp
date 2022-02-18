package com.josip.travelagency.security;

import com.josip.travelagency.config.TourAgencyAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    //This is another way of using access denied page
    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new TourAgencyAccessDeniedHandler();
    }

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .jdbcAuthentication().dataSource(dataSource)
//                .usersByUsernameQuery("select login, password, enabled from user where login=?")
//                .authoritiesByUsernameQuery("select login, role from role where login=?");
        auth
                .inMemoryAuthentication()
                .withUser("Josip").password(passwordEncoder().encode("admin")).roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.//authorizeRequests()
                csrf()
                .disable()
                .authorizeRequests()
                .anyRequest().permitAll()//authenticated();

//                .antMatchers("/", "/login")
//                    .permitAll()
//                .antMatchers("/addTour", "/editTour")
//                    .hasAnyRole("ADMIN, EMPLOYEE")
//                .antMatchers("/deleteTour", "/image/*")
//                    .hasAnyRole("ADMIN")
//                .antMatchers("/addComment", "/addUserToTour")
//                    .hasAnyRole("CLIENT")
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/checkUserAccount")
                    .defaultSuccessUrl("/")
                    .permitAll()
              .and()
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true)
                    .permitAll()
             .and()
                   // .exceptionHandling().accessDeniedPage("/forbidden");
                   .exceptionHandling().accessDeniedHandler(accessDeniedHandler());// 2nd way of access denied page
    }



}
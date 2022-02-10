package com.josip.travelagency.security;

import com.josip.travelagency.config.TourAgencyAccessDeniedHandler;
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

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("Josip").password(passwordEncoder().encode("admin")).roles("ADMIN", "EMPLOYEE")
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
              .antMatchers("/addTour")
                    .hasAnyRole("ADMIN", "EMPLOYEE")
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
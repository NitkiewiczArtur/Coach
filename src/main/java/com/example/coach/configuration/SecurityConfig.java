package com.example.coach.configuration;


import com.example.coach.model.User;
import com.example.coach.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserDetailsService customUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/index", "/adduser", "/main","/addWorkout","/showWorkoutResults", "/addWorkoutResult", "/home")
                    .hasAnyAuthority("ROLE_USER")
                .antMatchers("/users").hasAnyAuthority("ROLE_COACH")
                    .anyRequest().permitAll()
                .and()
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .loginProcessingUrl("/login-process")
                    .failureUrl("/login ?error")
                    .defaultSuccessUrl("/main")
                .and()
                .logout().logoutSuccessUrl("/login");
    }
//adsada
@Autowired
public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth
            .userDetailsService(customUserDetailsService)
            .passwordEncoder(passwordEncoder);
}
    }






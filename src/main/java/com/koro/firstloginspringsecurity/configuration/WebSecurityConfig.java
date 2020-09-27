package com.koro.firstloginspringsecurity.configuration;

import com.koro.firstloginspringsecurity.enums.AuthRole;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        User userAdmin = new User(
                "Jan",
                getPasswordEncoder().encode("Jan123"),
                Collections.singleton(new SimpleGrantedAuthority(AuthRole.ROLE_ADMIN.name())));

        User userUser = new User("Lena",
                getPasswordEncoder().encode("Lena123"),
                Collections.singleton(new SimpleGrantedAuthority(AuthRole.ROLE_USER.name() )));

        auth.inMemoryAuthentication().withUser(userAdmin);
        auth.inMemoryAuthentication().withUser(userUser);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/forAdmin").hasRole("ADMIN")
                .antMatchers("/forUser").hasAnyRole("ADMIN", "USER")
                .antMatchers("/forGuest").permitAll()
                .and()
                .formLogin().permitAll().defaultSuccessUrl("/forGuest")
                .and()
                .logout().logoutSuccessUrl("/endPage");
    }
}

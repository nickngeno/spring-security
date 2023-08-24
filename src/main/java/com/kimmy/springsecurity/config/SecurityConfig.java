package com.kimmy.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private  UserInfoDetailsService userInfoDetailsService;

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {

//        UserDetails admin =  User.builder()
//                .username("admin")
//                .password(encoder.encode("pass1"))
//                .roles("ADMIN")
//                .build();
//
//        UserDetails user =  User.builder()
//                .username("user")
//                .password(encoder.encode("pass2"))
//                .roles("USER")
//                .build();
//
//        return  new InMemoryUserDetailsManager(admin,user);
        return new UserInfoDetailsService();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http.csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/api/v1", "/api/v1/user").permitAll()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/products/**")
                .authenticated()
                .and()
                .formLogin()
                .and()
                .logout().clearAuthentication(true)
                .and()
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider (){

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService(passwordEncoder()));
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return  daoAuthenticationProvider;
    }
}

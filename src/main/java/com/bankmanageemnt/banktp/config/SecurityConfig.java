package com.bankmanageemnt.banktp.config;

import org.hibernate.annotations.Bag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       http.csrf(csrf->csrf.disable());
       /** http.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/clients/**").permitAll();
            auth.requestMatchers(HttpMethod.POST,"/clients/client/**").permitAll();
            auth.anyRequest().permitAll();
        }).formLogin();**/
        return http.build();
    }
}

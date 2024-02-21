package com.bankmanageemnt.banktp.config;

import com.bankmanageemnt.banktp.dao.ClientDao;
import org.hibernate.annotations.Bag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Sawadogo hamed   email <kerb418@gmail.com>
 **/
public class AuthenticateClientConfig {
    /**
     * Charger les CLients en Base de donnée
     * @param clientDao
     * @return
     */
    @Bean
    public UserDetailsService userDetailsService(ClientDao clientDao){
      return new ClientService(clientDao);
    }



}

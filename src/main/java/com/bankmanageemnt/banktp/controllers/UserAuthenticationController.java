package com.bankmanageemnt.banktp.controllers;

import com.bankmanageemnt.banktp.dto.LoginRequest;
import com.bankmanageemnt.banktp.model.Client;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sawadogo hamed   email <kerb418@gmail.com>
 **/
@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class UserAuthenticationController {

    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public Object login(@RequestBody LoginRequest loginRequest){
        /**
         * Authentifier Un client par son Username et son password
         */
        var authentication=this.authenticationManager.authenticate(
         new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword())
        );
        return authentication;
    }
    private record UserData(String username,String password){}
}

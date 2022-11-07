package com.example.auntificationservice.controller;

import com.example.auntificationservice.dto.TokenDto;
import com.example.auntificationservice.dto.UserDto;
import com.example.auntificationservice.model.User;
import com.example.auntificationservice.security.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {

    private final UserDetailsManager userDetailsManager;
    private final TokenGenerator tokenGenerator;
    private final DaoAuthenticationProvider authenticationProvider;

    @Autowired
    public AuthController(UserDetailsManager userDetailsManager, TokenGenerator tokenGenerator, DaoAuthenticationProvider authenticationProvider) {
        this.userDetailsManager = userDetailsManager;
        this.tokenGenerator = tokenGenerator;
        this.authenticationProvider = authenticationProvider;
    }

    @PostMapping("/register")
    public TokenDto register(@RequestBody UserDto userDto) {
        User user = new User(UUID.randomUUID().toString(), userDto.getUsername(), userDto.getPassword());
        userDetailsManager.createUser(user);

        Authentication authentication = UsernamePasswordAuthenticationToken.authenticated(user, userDto.getPassword(), Collections.emptyList());

        return tokenGenerator.createToken(authentication);
    }

    @PostMapping("/login")
    public TokenDto login(@RequestBody UserDto userDto) {
        Authentication authentication = authenticationProvider.authenticate(UsernamePasswordAuthenticationToken.unauthenticated(userDto.getUsername(), userDto.getPassword()));

        return tokenGenerator.createToken(authentication);
    }
}

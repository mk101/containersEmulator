package com.example.auntificationservice.controller;

import com.example.auntificationservice.dto.ErrorDto;
import com.example.auntificationservice.dto.TokenDto;
import com.example.auntificationservice.dto.UserDto;
import com.example.auntificationservice.model.User;
import com.example.auntificationservice.security.TokenGenerator;
import com.example.auntificationservice.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
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

    private final UserManager userDetailsManager;
    private final TokenGenerator tokenGenerator;
    private final DaoAuthenticationProvider authenticationProvider;
    private final JwtAuthenticationProvider jwtRefreshTokenAuthProvider;

    @Autowired
    public AuthController(UserManager userDetailsManager, TokenGenerator tokenGenerator, DaoAuthenticationProvider authenticationProvider, @Qualifier("jwtRefreshTokenAuthProvider") JwtAuthenticationProvider jwtRefreshTokenAuthProvider) {
        this.userDetailsManager = userDetailsManager;
        this.tokenGenerator = tokenGenerator;
        this.authenticationProvider = authenticationProvider;
        this.jwtRefreshTokenAuthProvider = jwtRefreshTokenAuthProvider;
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

    @PostMapping("/token")
    public TokenDto token(@RequestBody TokenDto tokenDto) {
        Authentication authentication = jwtRefreshTokenAuthProvider.authenticate(new BearerTokenAuthenticationToken(tokenDto.getRefreshToken()));

        return tokenGenerator.createToken(authentication);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<ErrorDto> authenticate(@AuthenticationPrincipal User user) {
        if ( !userDetailsManager.userExistsById(user.getId()) ) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new ErrorDto("Unauthorized")
            );
        }
        return ResponseEntity.ok(new ErrorDto("User found"));
    }
}

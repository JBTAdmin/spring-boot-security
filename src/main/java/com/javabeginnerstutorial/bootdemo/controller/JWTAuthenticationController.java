package com.javabeginnerstutorial.bootdemo.controller;


import com.javabeginnerstutorial.bootdemo.config.jwt.TokenProvider;
import com.javabeginnerstutorial.bootdemo.model.JWTAuthenticationRequest;
import com.javabeginnerstutorial.bootdemo.repository.UserRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.ok;

@ConditionalOnProperty(name="appsecurity.method", havingValue = "JWT")
@RestController
@RequestMapping("/auth")
public class JWTAuthenticationController {

    AuthenticationManager authenticationManager;
    TokenProvider tokenProvider;
    UserRepository users;

    public JWTAuthenticationController(AuthenticationManager authenticationManager, TokenProvider tokenProvider, UserRepository users) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.users = users;
    }

    @PostMapping("/givemetoken")
    public ResponseEntity signin(@RequestBody JWTAuthenticationRequest data) {

        try {
            String username = data.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            String token = tokenProvider.createToken(username, users.findByName(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Username " + username + "not found"))
                    .getRoles().stream().map(role -> role.getRole()).collect(Collectors.toList()));

            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("token", token);
            return ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }
}

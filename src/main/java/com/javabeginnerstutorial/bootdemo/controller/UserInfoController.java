package com.javabeginnerstutorial.bootdemo.controller;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@ConditionalOnProperty(name="appsecurity.method", havingValue = "JWT")
public class UserInfoController {

	@GetMapping("/me")
    public ResponseEntity currentUser(@AuthenticationPrincipal UserDetails userDetails){
        Map<Object, Object> model = new HashMap<>();
        model.put("UserName", userDetails.getUsername());
        model.put("Roles", userDetails.getAuthorities()
            .stream()
            .map(authority -> ((GrantedAuthority) authority).getAuthority())
            .collect(toList())
        );
        return ok(model);
    }
}

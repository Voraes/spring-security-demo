package com.example.securitydemo.controller;

import com.example.securitydemo.model.ApplicationUser;
import com.example.securitydemo.service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register ")
    public ApplicationUser registerUser() {
        return null; //TODO
    }
}

package com.example.securitydemo.controller;

import com.example.securitydemo.model.ApplicationUser;
import com.example.securitydemo.model.RegistrationDTO;
import com.example.securitydemo.service.AuthenticationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @GetMapping
    public String lala() {
        String s = "EYYEYEYEYEYEYE";
        return s;
    }
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register ")
    public ApplicationUser registerUser(@RequestBody RegistrationDTO body) {
        System.out.println("yeye");
        return authenticationService.registerUser(body.getUsername(), body.getPassword());
    }
}

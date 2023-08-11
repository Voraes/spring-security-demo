package com.example.securitydemo.model;

import lombok.Data;

@Data
public class LoginResponseDTO {

    private ApplicationUser user;
    private String jwt;
}

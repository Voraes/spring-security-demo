package com.example.securitydemo.service;

import com.example.securitydemo.model.ApplicationUser;
import com.example.securitydemo.model.LoginResponseDTO;
import com.example.securitydemo.model.Role;
import com.example.securitydemo.repository.RoleRepository;
import com.example.securitydemo.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class AuthenticationService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    public AuthenticationService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public ApplicationUser registerUser(String username, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        //Role userRole = roleRepository.findByAuthority("USER").get();

        Role userRole = new Role();
        userRole.setAuthority("USER");

        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);

        ApplicationUser applicationUser = new ApplicationUser();
        applicationUser.setUserId(0L);
        applicationUser.setUsername(username);
        applicationUser.setPassword(encodedPassword);
        applicationUser.setAuthorities(authorities);

        return userRepository.save(applicationUser);
    }

    public LoginResponseDTO loginUser(String username, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            String token = tokenService.generateJwt(authentication);

            LoginResponseDTO response = new LoginResponseDTO();
            response.setUser(userRepository.findByUsername(username).get());
            response.setJwt(token);

            return response;
        } catch (AuthenticationException exception) {
            LoginResponseDTO loginException = new LoginResponseDTO();
            loginException.setUser(null);
            loginException.setJwt("");
            return loginException;
        }
    }
}

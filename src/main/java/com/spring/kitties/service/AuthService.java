package com.spring.kitties.service;

import com.spring.kitties.controller.payload.LoginRequest;
import com.spring.kitties.persistence.UserRepository;
import com.spring.kitties.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    public User authenticate(LoginRequest loginRequest) {
        var authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
            )
        );

        return userRepository.findByUsername(authentication.getName()).get();
    }

    public boolean isAuthenticated() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (auth != null &&
                auth.isAuthenticated() &&
                !(auth instanceof AnonymousAuthenticationToken));
    }
}

package com.spring.kitties.controller;

import com.spring.kitties.model.User;
import com.spring.kitties.service.AuthService;
import com.spring.kitties.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequestMapping("/api/v1/kitties")
@RestController
public class KittiesController {
    @Autowired
    UserService userService;

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        System.out.println("test");
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{username}")
    public Optional<User> getByUsername(@PathVariable String username) {
        return userService.loadUser(username);
    }

}

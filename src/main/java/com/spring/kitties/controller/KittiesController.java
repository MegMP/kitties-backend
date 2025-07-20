package com.spring.kitties.controller;

import com.spring.kitties.service.AuthService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/kitties")
@RestController
public class KittiesController {
    @GetMapping("/test")
    public String test() {
        System.out.println("test endpoint called");
        return "notAuth";
    }
}

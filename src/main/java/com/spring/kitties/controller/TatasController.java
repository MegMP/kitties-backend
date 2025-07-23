package com.spring.kitties.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/tatas/v1")
@RestController
public class TatasController {

    @GetMapping("/secured/user")
    public List<String> getSecuredByUser() {
        return List.of("Tata User 1", "Tata User 2", "Tata User 3");
    }

    @GetMapping("/secured/admin")
    public List<String> getSecuredByAdmin() {
        return List.of("Tata Admin 1", "Tata Admin 2", "Tata Admin 3");
    }

    @GetMapping("/unsecured")
    public List<String> getUnsecured() {
        return List.of("Tata Unsecured 1", "Tata Unsecured 2", "Tata Unsecured 3");
    }
}

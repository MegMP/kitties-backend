package com.spring.kitties.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/kitties")
@RestController
public class KittiesController {
    @GetMapping("/{id}")
    public String home() {
        return "redirect:/account";
    }
}

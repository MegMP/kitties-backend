package com.spring.kitties.controller;

import com.spring.kitties.exception.DuplicateUserFieldException;
import com.spring.kitties.exception.UserNotFoundException;
import com.spring.kitties.model.User;
import com.spring.kitties.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {
    private final UserService userService;
    public AccountController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<User> getAccountInfo(@RequestHeader("") long id) {
        try {
            User user = userService.loadUser(id);
            if (user == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException e) {
            System.out.println("Error: User not found");
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping({"/"})
    public ResponseEntity<Void> updateUpdate(@RequestBody Map<String, String> updates, @RequestHeader("id") long id, RedirectAttributes redirectAttributes) {
        try {
            this.userService.updateUser(id, updates);
            return ResponseEntity.noContent().build();
        } catch (DuplicateUserFieldException var6) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
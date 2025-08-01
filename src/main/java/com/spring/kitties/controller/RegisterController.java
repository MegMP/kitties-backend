package com.spring.kitties.controller;

import com.spring.kitties.exception.DuplicateEmailException;
import com.spring.kitties.exception.DuplicateUsernameException;
import com.spring.kitties.model.RegisterRequest;
import com.spring.kitties.model.User;
import com.spring.kitties.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/register")
public class RegisterController {
    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping("/register")
//    public String home() {
//        return "redirect:/account";
//    }

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request)
    {
        System.out.println("t");
        try {
            userService.addUser(new User(request.getFirstname(),
                    request.getLastname(),
                    request.getUsername(),
                    request.getEmail(),
                    request.getPassword(),
                    request.getCity()));
            return ResponseEntity.ok().build();
        } catch (DuplicateEmailException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("This email is already in use");
        } catch (DuplicateUsernameException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("This username is already in use");
        }
    }
}
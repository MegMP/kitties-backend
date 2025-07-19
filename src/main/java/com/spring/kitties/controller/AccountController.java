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

    @GetMapping("")
    public ResponseEntity<User> getAccountInfo(@RequestHeader("id") long id) {
        try {
            User user = userService.loadUser(id);
            System.out.println("Update username for id: " + id );
            if (user == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping({"/firstname"})
    public ResponseEntity<Void> updateFirstname(@RequestBody Map<String, String> body, @RequestHeader("id") long id, RedirectAttributes redirectAttributes) {
        try {
            String firstname = (String)body.get("firstname");
            this.userService.updateFirstname(id, firstname);
            return ResponseEntity.noContent().build();
        } catch (DuplicateUserFieldException var6) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PatchMapping({"/lastname"})
    public ResponseEntity<Void> updateLastname(@RequestBody Map<String, String> body, @RequestHeader("id") long id, RedirectAttributes redirectAttributes) {
        try {
            String lastname = (String)body.get("lastname");
            this.userService.updateLastname(id, lastname);
            return ResponseEntity.noContent().build();
        } catch (DuplicateUserFieldException var6) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PatchMapping("/username")
    public ResponseEntity<Void> updateUsername(@RequestBody Map<String, String> body, @RequestHeader("id") long id, RedirectAttributes redirectAttributes) {
        try {
            String username = body.get("username");
            userService.updateUsername(id, username);
            return ResponseEntity.noContent().build();
        }  catch (DuplicateUserFieldException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PatchMapping("/email")
    public ResponseEntity<Void> updateEmail(@RequestBody Map<String, String> body, @RequestHeader("id") long id, RedirectAttributes redirectAttributes) {
        try {
            String email = body.get("email");
            userService.updateEmail(id, email);
            return ResponseEntity.noContent().build();
        }  catch (DuplicateUserFieldException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PatchMapping({"/password"})
    public ResponseEntity<Void> updatePassword(@RequestBody Map<String, String> body, @RequestHeader("id") long id, RedirectAttributes redirectAttributes) {
        try {
            String password = (String)body.get("password");
            this.userService.updatePassword(id, password);
            return ResponseEntity.noContent().build();
        } catch (DuplicateUserFieldException var6) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PatchMapping({"/city"})
    public ResponseEntity<Void> updateCity(@RequestBody Map<String, String> body, @RequestHeader("id") long id, RedirectAttributes redirectAttributes) {
        try {
            String city = (String)body.get("city");
            this.userService.updateCity(id, city);
            return ResponseEntity.noContent().build();
        } catch (DuplicateUserFieldException var6) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
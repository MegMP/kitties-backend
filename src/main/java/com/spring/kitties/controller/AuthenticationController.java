package com.spring.kitties.controller;

import com.spring.kitties.controller.payload.LoginRequest;
import com.spring.kitties.controller.payload.LoginResponse;
import com.spring.kitties.model.User;
import com.spring.kitties.persistence.UserRepository;
import com.spring.kitties.service.AuthService;
import com.spring.kitties.service.JwtService;
import com.spring.kitties.service.UserService;
import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/v1/auth")
@RestController
public class AuthenticationController {

    @Autowired
    private AuthService authService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserService userService;

    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            User user = authService.authenticate(loginRequest);
            return ResponseEntity.ok(LoginResponse.builder()
                    .token(jwtService.generateToken(user)));


        } catch (BadCredentialsException ex) {
            Map<String, String> error = new HashMap<>();
            error.put("password", "Invalid username or password");
            error.put("username", "");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }
    }

    @GetMapping(path = "/heartbeat")
    public ResponseEntity<String> heartbeat() {
        return ResponseEntity.ok("Heartbeat successful");
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.loadUsers();
    }
}

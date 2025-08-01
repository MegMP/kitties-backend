package com.spring.kitties.controller;

import com.spring.kitties.controller.payload.LoginRequest;
import com.spring.kitties.controller.payload.LoginResponse;
import com.spring.kitties.model.User;
import com.spring.kitties.persistence.UserRepository;
import com.spring.kitties.service.AuthService;
import com.spring.kitties.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1/auth")
@RestController
public class AuthenticationController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        User user = authService.authenticate(loginRequest);

        return ResponseEntity.ok(new LoginResponse(
            user.getUsername(),
            jwtService.generateToken(user)
        ));
    }

    @GetMapping(path = "/heartbeat")
    public ResponseEntity<String> heartbeat() {
        if (authService.isAuthenticated()) {
            return ResponseEntity.ok("Heartbeat successful");
        } else {
            return ResponseEntity.status(401).body("Unauthorized");
        }
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}

package com.spring.kitties.controller;

import com.spring.kitties.model.User;
import com.spring.kitties.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import com.spring.kitties.service.JwtService;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api/v1/users")
@RestController
public class UserController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    private Optional<User> getUser(String authHeader) {
        String username = "";
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            username = jwtService.extractUsername(token);
        }
        Optional<User> user = userService.loadUser(username);
        return user;
    }

    @GetMapping("/data")
    public ResponseEntity<Optional<User>> data(@RequestHeader("Authorization") String authHeader) {
        return ResponseEntity.ok().body(getUser(authHeader));
    }

    @GetMapping("/photos")
    public ResponseEntity<List<String>> getPhotos(@RequestHeader("Authorization") String authHeader) {
        Optional<User> user = getUser(authHeader);

    }

//    @GetMapping("/data")
//    public ResponseEntity<Optional<User>> data() {
//        System.out.println("data");
//        return ResponseEntity.ok().build();
//    }
}

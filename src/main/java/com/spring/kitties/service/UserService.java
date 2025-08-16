package com.spring.kitties.service;

import com.spring.kitties.model.User;
import com.spring.kitties.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spring.kitties.service.JwtService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private JwtService jwtService;

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> loadUsers() {
        List<User> users= userRepository.findAll();
        return users;
    }

    public Optional<User> loadUser(String username) {
        return userRepository.findByUsername(username);
    }

    public void addUser(User user) {
//        userRepository.insertUser(user);
    }

    public void deleteUser(User user) {}

    public void updateUser(long id, Map<String, String> updates) {
//        userRepository.updateUser(id, updates);
    }


    public String getUsernameFromToken(String authHeadr) {
        String token = authHeadr.substring(7);
        return jwtService.extractUsername(token);
    }
}

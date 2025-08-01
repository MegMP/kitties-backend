package com.spring.kitties.service;

import com.spring.kitties.model.User;
import com.spring.kitties.persistence.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository = new UserRepository();

    public List<User> loadUsers() {
        List<User> users= userRepository.findAll();
        return users;
    }

    public Optional<User> loadUser(long id) {
        return userRepository.findById(id);
    }

    public Optional<User> loadUser(String username) {
        return userRepository.findByUsername(username);
    }

    public void addUser(User user) {
        userRepository.insertUser(user);
    }

    public void deleteUser(User user) {}

    public void updateUser(long id, Map<String, String> updates) {
        userRepository.updateUser(id, updates);
    }
}

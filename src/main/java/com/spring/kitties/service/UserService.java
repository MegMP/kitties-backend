package com.spring.kitties.service;

import com.spring.kitties.exception.DuplicateUserFieldException;
import com.spring.kitties.exception.UserNotFoundException;
import com.spring.kitties.model.User;
import com.spring.kitties.persistence.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService {
    private final UserRepository userRepository = new UserRepository();

    public List<User> loadUsers() {
        List<User> users= userRepository.findAll();
        return users;
    }

    public User loadUser(long id) {
        return userRepository.findById(id);
    }

    public User loadUser(String username) {
        return userRepository.findByUsername(username);
    }

    public void addUser(User user) {
        List<User> users = userRepository.findAll();

        if(emailExists(user.getEmail())) {
            throw new DuplicateUserFieldException("Email already exists");
        }
        if(usernameExists(user.getUsername())) {
            throw new DuplicateUserFieldException("Username already exists");
        }

        users.add(user);
        userRepository.saveUsers(users);
    }

    public void deleteUser(User user) {}

    public void updateUser(long id, Map<String, String> updates) {
        User user = userRepository.findById(id);
        if (updates.containsKey("firstname")) {
            user.setFirstname(updates.get("firstname"));
        }
        if (updates.containsKey("lastname")) {
            user.setLastname(updates.get("lastname"));
        }
        if (updates.containsKey("username")) {
            String newUsername = updates.get("username");
            if (usernameExists(newUsername)) {
                throw new DuplicateUserFieldException("Username already exists");
            }
            user.setUsername(newUsername);
        }
        if (updates.containsKey("email")) {
            String newEmail= updates.get("email");
            if (emailExists(newEmail)) {
                throw new DuplicateUserFieldException("Username already exists");
            }
            user.setEmail(newEmail);
        }
        if (updates.containsKey("password")) {
            user.setPassword(updates.get("password"));
        }
        if (updates.containsKey("city")) {
            user.setCity(updates.get("city"));
        }
        throw new UserNotFoundException("User with id " + id + " not found");
    }

    public boolean emailExists(String email) {
        List<User> users = loadUsers();
        for (User user: users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    public boolean usernameExists(String username) {
        List<User> users = loadUsers();
        for (User user: users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }
}

package com.spring.kitties.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.kitties.exception.DuplicateEmailException;
import com.spring.kitties.exception.DuplicateUsernameException;
import com.spring.kitties.exception.UserNotFoundException;
import com.spring.kitties.model.User;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepository {
    private final ObjectMapper mapper = new ObjectMapper();
    private final File userFile = new File("users.json");

    public void saveUsers(List<User> users) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(userFile, users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<User> findAll() {
        try {
            List<User> users;
            if (userFile.exists()) {
                users = mapper.readValue(userFile, new TypeReference<List<User>>() {});
            } else {
                users = new ArrayList<>();
            }
            return users;
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public boolean emailExists(String email) {
        List<User> users = findAll();
        return users.stream()
                .anyMatch(user -> user.getEmail().equalsIgnoreCase(email));
    }

    public boolean usernameExists(String username) {
        List<User> users = findAll();
        return users.stream()
                .anyMatch(user -> user.getUsername().equals(username));
    }

    public Optional<User> findById(long id) {
        List<User> users = findAll();

        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst();
    }

    public Optional<User> findByUsername(String username) {
        List<User> users = findAll();

        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }

    public void insertUser(User user) {
        List<User> users = findAll();

        if(emailExists(user.getEmail())) {
            throw new DuplicateEmailException("Email already exists");
        }
        if(usernameExists(user.getUsername())) {
            throw new DuplicateUsernameException("Username already exists");
        }

        users.add(user);
        saveUsers(users);
    }

    public void updateUser(long id, Map<String, String> updates) {
        Optional<User> optionalUser = findById(id);
        List<User> users = findAll();

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (updates.containsKey("firstname")) {
                user.setFirstname(updates.get("firstname"));
            }
            if (updates.containsKey("lastname")) {
                user.setLastname(updates.get("lastname"));
            }
            if (updates.containsKey("username")) {
                String newUsername = updates.get("username");
                if (usernameExists(newUsername)) {
                    throw new DuplicateUsernameException("Username already exists");
                }
                user.setUsername(newUsername);
            }
            if (updates.containsKey("email")) {
                String newEmail = updates.get("email");
                if (emailExists(newEmail)) {
                    throw new DuplicateEmailException("Username already exists");
                }
                user.setEmail(newEmail);
            }
            if (updates.containsKey("password")) {
                user.setPassword(updates.get("password"));
            }
            if (updates.containsKey("city")) {
                user.setCity(updates.get("city"));
            }
        }
        else {
            throw new UserNotFoundException("User with id " + id + " not found");
        }
        saveUsers(users);
    }
}

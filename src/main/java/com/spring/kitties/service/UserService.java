package com.spring.kitties.service;

import com.spring.kitties.exception.DuplicateUserFieldException;
import com.spring.kitties.exception.UserNotFoundException;
import com.spring.kitties.model.User;
import com.spring.kitties.persistence.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void updateFirstname(long id, String newFirstname) {
        List<User> users = userRepository.findAll();

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id) {
                users.get(i).setFirstname(newFirstname);
                userRepository.saveUsers(users);
                return;
            }
        }
        throw new UserNotFoundException("User with id " + id + " not found");
    }

    public void updateLastname(long id, String newLastname) {
        List<User> users = userRepository.findAll();

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id) {
                users.get(i).setLastname(newLastname);
                userRepository.saveUsers(users);
                return;
            }
        }
        throw new UserNotFoundException("User with id " + id + " not found");
    }

    public void updateUsername(long id, String newUsername) {
        List<User> users = userRepository.findAll();

        if (usernameExists(newUsername)) {
            throw new DuplicateUserFieldException("Username already exists");
        }

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id) {
                users.get(i).setUsername(newUsername);
                userRepository.saveUsers(users);
                return;
            }
        }
        throw new UserNotFoundException("User with id " + id + " not found");
    }

    public void updateEmail(long id, String newEmail) {
        List<User> users = userRepository.findAll();

        if (emailExists(newEmail)) {
            throw new DuplicateUserFieldException("Email already exists");
        }

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id) {
                users.get(i).setEmail(newEmail);
                userRepository.saveUsers(users);
                return;
            }
        }
        throw new UserNotFoundException("User with id " + id + " not found");
    }

    public void updatePassword(long id, String newPassword) {
        List<User> users = loadUsers();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id) {
                users.get(i).setPassword(newPassword);
                userRepository.saveUsers(users);
                return;
            }
        }
        throw new UserNotFoundException("User with id " + id + " not found");
    }

    public void updateCity(long id, String newCity) {
        List<User> users = loadUsers();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id) {
                users.get(i).setCity(newCity);
                userRepository.saveUsers(users);
                return;
            }
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

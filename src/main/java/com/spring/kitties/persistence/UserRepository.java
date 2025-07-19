package com.spring.kitties.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.kitties.model.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private final ObjectMapper mapper = new ObjectMapper();
    private final File userFile = new File("users.json");

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
            return null;
        }
    }

    public User findById(long id) {
        List<User> users = findAll();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id) {
                return users.get(i);
            }
        }
        return null;
    }

    public void saveUsers(List<User> users) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(userFile, users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //save
}

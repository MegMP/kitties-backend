package com.spring.kitties.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.kitties.model.User;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Repository
public class UserRepository {
    private final ObjectMapper mapper = new ObjectMapper();
    private final String usersFilePath = "repository/users.json";

    public List<User> findAll() {
        InputStream usersStream = getClass().getClassLoader().getResourceAsStream(usersFilePath);

        try {
            List<User> users = mapper.readValue(usersStream, new TypeReference<List<User>>() {});
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

    public User findByUsername(String username) {
        List<User> users = findAll();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                return users.get(i);
            }
        }
        return null;
    }

    public void saveUsers(List<User> users) {
//        try {
////            mapper.writerWithDefaultPrettyPrinter().writeValue(userFile, users);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    //save
}

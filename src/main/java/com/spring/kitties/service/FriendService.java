package com.spring.kitties.service;
import com.spring.kitties.model.User;
import com.spring.kitties.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FriendService {
    @Autowired
    private UserRepository userRepository;

//    public List<User> findAllFriends(String username) {
//        return userRepository.findAllWithFriendsAndProfilesByUsername(username);
//    }
}

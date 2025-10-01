package com.spring.kitties.service;
import com.spring.kitties.model.User;
import com.spring.kitties.model.UserProfile;
import com.spring.kitties.model.UserWithProfile;
import com.spring.kitties.persistence.FriendRepository;
import com.spring.kitties.persistence.UserProfileRepository;
import com.spring.kitties.persistence.UserRepository;
import com.spring.kitties.persistence.entity.FriendEntity;
import com.spring.kitties.persistence.entity.FriendKey;
import com.spring.kitties.service.mapper.UserEntityMapper;
import com.spring.kitties.service.mapper.UserProfileEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FriendService {
    @Autowired
    private UserProfileRepository userProfileRepository;
    @Autowired
    private UserProfileEntityMapper userProfileEntityMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserEntityMapper userEntityMapper;
    @Autowired
    private FriendRepository friendRepository;

    public List<UserProfile> findAllFriends(String userId) {
        return userProfileRepository.findAllFriends(userId)
                .stream()
                .map(entity -> userProfileEntityMapper.fromEntity(entity))
                .toList();
    }

    public List<UserWithProfile> findAllNonFriends(String userId) {
        List<User> users = userRepository.findAllNonFriends(userId).stream()
                .map(entity -> userEntityMapper.fromEntity(entity))
                .toList();

        List<UserWithProfile> usersWithProfiles = new ArrayList<>();
        for (User user : users) {
            UserProfile userProfile = userProfileRepository.findByUserId(user.getId())
                    .map(entity -> userProfileEntityMapper.fromEntity(entity))
                    .get();
            UserWithProfile userWithProfile = UserWithProfile.builder()
                    .user(user)
                    .userProfile(userProfile)
                    .build();

            usersWithProfiles.add(userWithProfile);
        }
        return usersWithProfiles;
    }

    public void deleteFriend(String userId, String friendId) {
        friendRepository.deleteFriend(userId, friendId);
    }

    public void addFriend(String userId, String friendId) {
        FriendKey friendKey = new FriendKey(userId, friendId);
        FriendEntity friend = new FriendEntity(friendKey, false);

        if (userRepository.isUnacceptedFriend(userId) == true) {
            friend.setAccepted(true);
            friendRepository.acceptFriend(userId, friendId);
        }

        friendRepository.save(friend);
    }
}

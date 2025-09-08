package com.spring.kitties.service;

import com.spring.kitties.config.SecurityConfig;
import com.spring.kitties.exception.DuplicateEmailException;
import com.spring.kitties.exception.DuplicateUsernameException;
import com.spring.kitties.model.User;
import com.spring.kitties.persistence.entity.UserEntity;
import com.spring.kitties.model.UserProfile;
import com.spring.kitties.persistence.entity.UserProfileEntity;
import com.spring.kitties.persistence.UserProfileRepository;
import com.spring.kitties.persistence.UserRepository;
import com.spring.kitties.service.mapper.UserEntityMapper;
import com.spring.kitties.service.mapper.UserProfileEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserProfileRepository userProfileRepository;
    @Autowired
    private SecurityConfig  securityConfig;
    @Autowired
    private UserEntityMapper userEntityMapper;
    @Autowired
    private UserProfileEntityMapper userProfileEntityMapper;

    public List<User> loadUsers() {
        List<User> users = userRepository.findAll().stream()
                .map(entity -> userEntityMapper.fromEntity(entity))
                .toList();
        return users;
    }

    public Optional<User> loadUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(entity -> userEntityMapper.fromEntity(entity));
    }

    public Optional<UserProfile> loadUserProfileById(String id) {
        return userProfileRepository.findByUserId(id)
                .map(entity -> userProfileEntityMapper.fromEntity(entity));
    }

    public void addUser(User user, UserProfile userProfile) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new DuplicateUsernameException("Username already exists");
        }
        if (userProfileRepository.existsByEmail(userProfile.getEmail())) {
            throw new DuplicateEmailException("Email already exists");
        }

        user.setPassword(securityConfig.passwordEncoder().encode(user.getPassword()));
        UserEntity userEntity = userEntityMapper.toEntity(user);
        userEntity = userRepository.save(userEntity);

        UserProfileEntity userProfileEntity = userProfileEntityMapper.toEntity(userProfile, null);
        userProfileEntity.setUser(userEntity);
        userProfileRepository.save(userProfileEntity);
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

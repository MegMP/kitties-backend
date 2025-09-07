package com.spring.kitties.service.mapper;

import com.spring.kitties.model.UserProfile;
import com.spring.kitties.model.UserProfileEntity;
import org.springframework.stereotype.Component;

@Component
public class UserProfileEntityMapper {
    public UserProfile fromEntity(UserProfileEntity userProfileEntity) {
        return UserProfile.builder()
                .firstname(userProfileEntity.getFirstname())
                .lastname(userProfileEntity.getLastname())
                .email(userProfileEntity.getEmail())
                .city(userProfileEntity.getCity())
                .build();
    }

    public UserProfileEntity toEntity(UserProfile userProfile, String id) {
        return UserProfileEntity.builder()
                .userId(id)
                .firstname(userProfile.getFirstname())
                .lastname(userProfile.getLastname())
                .email(userProfile.getEmail())
                .city(userProfile.getCity())
                .build();
    }
}

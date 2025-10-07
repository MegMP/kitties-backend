package com.spring.kitties.controller.mapper;

import com.spring.kitties.controller.payload.RegisterRequest;
import com.spring.kitties.controller.payload.UserUpdateRequest;
import com.spring.kitties.model.UserProfile;
import org.springframework.stereotype.Component;

@Component
public class UserProfileUpdateMapper {
    public UserProfile toUserProfile(UserUpdateRequest userUpdateRequest) {
        return UserProfile.builder()
                .firstname(userUpdateRequest.getFirstname())
                .lastname(userUpdateRequest.getLastname())
                .email(userUpdateRequest.getEmail())
                .city(userUpdateRequest.getCity())
                .build();
    }
}

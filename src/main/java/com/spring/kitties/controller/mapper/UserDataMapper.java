package com.spring.kitties.controller.mapper;

import com.spring.kitties.controller.payload.UserDataResponse;
import com.spring.kitties.model.User;
import com.spring.kitties.model.UserProfile;
import org.springframework.stereotype.Component;

@Component
public class UserDataMapper {
    public UserDataResponse toUserResponse(User user, UserProfile userProfile) {
        return UserDataResponse.builder()
                .username(user.getUsername())
                .firstname(userProfile.getFirstname())
                .lastname(userProfile.getLastname())
                .email(userProfile.getEmail())
                .city(userProfile.getCity())
                .build();
    }
}

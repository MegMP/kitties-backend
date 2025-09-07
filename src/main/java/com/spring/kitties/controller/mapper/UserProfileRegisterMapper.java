package com.spring.kitties.controller.mapper;

import com.spring.kitties.controller.payload.RegisterRequest;
import com.spring.kitties.model.UserProfile;
import org.springframework.stereotype.Component;

@Component
public class UserProfileRegisterMapper {
    public UserProfile toUserProfile(RegisterRequest registerRequest) {
        return UserProfile.builder()
                .firstname(registerRequest.getFirstname())
                .lastname(registerRequest.getLastname())
                .email(registerRequest.getEmail())
                .city(registerRequest.getCity())
                .build();
    }
}

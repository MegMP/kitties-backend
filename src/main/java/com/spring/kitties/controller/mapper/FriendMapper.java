package com.spring.kitties.controller.mapper;

import com.spring.kitties.controller.payload.FriendResponse;
import com.spring.kitties.controller.payload.FullFriendResponse;
import com.spring.kitties.controller.payload.UserDataResponse;
import com.spring.kitties.model.UserProfile;
import com.spring.kitties.model.UserWithProfile;
import org.springframework.stereotype.Component;

@Component
public class FriendMapper {
    public FriendResponse toFriend(UserProfile userProfile) {
        return FriendResponse.builder()
                .userId(userProfile.getUserId())
                .firstname(userProfile.getFirstname())
                .lastname(userProfile.getLastname())
                .build();
    }

    public FullFriendResponse toFullUser(UserWithProfile userWithProfile) {
        return FullFriendResponse.builder()
                .id(userWithProfile.getUser().getId())
                .username(userWithProfile.getUser().getUsername())
                .firstname(userWithProfile.getUserProfile().getFirstname())
                .lastname(userWithProfile.getUserProfile().getLastname())
                .email(userWithProfile.getUserProfile().getEmail())
                .city(userWithProfile.getUserProfile().getCity())
                .build();
    }
}

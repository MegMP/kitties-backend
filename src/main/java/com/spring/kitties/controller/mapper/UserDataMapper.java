package com.spring.kitties.controller.mapper;

import com.spring.kitties.controller.payload.UserDataResponse;
import com.spring.kitties.model.User;

public class UserDataMapper {
    public UserDataResponse toUserResponse(User user) {
        return UserDataResponse.builder()
                .firstname(user.)
    }
}

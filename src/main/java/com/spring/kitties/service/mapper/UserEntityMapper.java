package com.spring.kitties.service.mapper;

import com.spring.kitties.model.User;
import com.spring.kitties.persistence.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserEntityMapper {
    public User fromEntity(UserEntity userEntity) {
        return User.builder()
                .id(userEntity.getUserId())
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .build();
    }

    public UserEntity toEntity(User user) {
        return UserEntity.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }
}

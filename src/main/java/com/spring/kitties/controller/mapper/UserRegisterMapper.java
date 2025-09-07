package com.spring.kitties.controller.mapper;
import com.spring.kitties.controller.payload.RegisterRequest;
import com.spring.kitties.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserRegisterMapper {
    public User toUser(RegisterRequest registerRequest) {
        return User.builder()
                .username(registerRequest.getUsername())
                .password(registerRequest.getPassword())
                .build();
    }
}

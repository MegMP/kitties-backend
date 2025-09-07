package com.spring.kitties.controller.payload;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LoginRequest {

    private String username;
    private String password;
}

package com.spring.kitties.controller.payload;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RegisterRequest {
    private String username;
    private String firstname;
    private String lastname;
    private String password;
    private String email;
    private String city;
}

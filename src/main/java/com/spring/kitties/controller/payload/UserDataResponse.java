package com.spring.kitties.controller.payload;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UserDataResponse {
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String city;
}

package com.spring.kitties.model;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Builder
@Setter
@Getter
public class User {
    private String id;
    @NonNull
    private String username;
    @NonNull
    private String password;
}

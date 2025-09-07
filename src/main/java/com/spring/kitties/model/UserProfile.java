package com.spring.kitties.model;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Builder
@Setter
@Getter
public class UserProfile {
    @NonNull
    private String lastname;
    @NonNull
    private String firstname;
    @NonNull
    private String email;
    @NonNull
    private String city;
}

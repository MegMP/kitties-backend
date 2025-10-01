package com.spring.kitties.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UserWithProfile {
    private User user;
    private UserProfile userProfile;
}

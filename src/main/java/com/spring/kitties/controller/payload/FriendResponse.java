package com.spring.kitties.controller.payload;

import com.spring.kitties.model.UserProfile;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;

@Builder
@Getter
public class FriendResponse {
    @NonNull
    private String userId;
    @NonNull
    private String firstname;
    @NonNull
    private String lastname;
}

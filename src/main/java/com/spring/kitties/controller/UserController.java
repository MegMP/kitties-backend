package com.spring.kitties.controller;

import com.spring.kitties.controller.mapper.*;
import com.spring.kitties.controller.payload.*;
import com.spring.kitties.model.User;
import com.spring.kitties.model.UserProfile;
import com.spring.kitties.persistence.UserProfileRepository;
import com.spring.kitties.persistence.UserRepository;
import com.spring.kitties.service.FriendService;
import com.spring.kitties.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import com.spring.kitties.service.JwtService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequestMapping("/api/v1/users")
@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserDataMapper userDataMapper;
    @Autowired
    private UserProfileUpdateMapper userProfileUpdateMapper;

    @GetMapping("/me")
    private ResponseEntity<UserDataResponse> getData(@RequestHeader("Authorization") String authHeader) {
        String username = userService.getUsernameFromToken(authHeader);

        User user = userService.loadUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: "));
        UserProfile userProfile = userService.loadUserProfileById(user.getId())
                .orElseThrow(() -> new UsernameNotFoundException("User not found: "));

        return ResponseEntity.ok().body(userDataMapper.toUserResponse(user, userProfile));
    }

    @PostMapping("/me")
    private ResponseEntity<String> editUser(@RequestHeader("Authorization") String authHeader, @RequestBody UserUpdateRequest request) {
        userService.updateUser(userService.getUserIdFromToken(authHeader), request.getUsername(), userProfileUpdateMapper.toUserProfile(request));
        return ResponseEntity.ok().body("success");
    }

    @PostMapping("/me/password")
    private ResponseEntity<String> editPassword(@RequestHeader("Authorization") String authHeader, @RequestBody String request) {
        userService.updatePassword(userService.getUserIdFromToken(authHeader), request);
        return ResponseEntity.ok().body("success");
    }


//    @GetMapping("/photos")
//    public ResponseEntity<List<String>> getPhotos(@RequestHeader("Authorization") String authHeader) {
//        Optional<User> user = getUser(authHeader);
//
//    }

}

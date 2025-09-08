package com.spring.kitties.controller;

import com.spring.kitties.controller.mapper.UserDataMapper;
import com.spring.kitties.controller.payload.UserDataResponse;
import com.spring.kitties.model.User;
import com.spring.kitties.model.UserProfile;
import com.spring.kitties.persistence.UserRepository;
import com.spring.kitties.service.FriendService;
import com.spring.kitties.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import com.spring.kitties.service.JwtService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequestMapping("/api/v1/users")
@RestController
public class UserController {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserService userService;
    @Autowired
    private FriendService friendService;
    @Autowired
    private UserDataMapper userDataMapper;

    @GetMapping("/data")
    private ResponseEntity<UserDataResponse> getData(@RequestHeader("Authorization") String authHeader) {
        String username = userService.getUsernameFromToken(authHeader);

        User user = userService.loadUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: "));
        UserProfile userProfile = userService.loadUserProfileById(user.getId())
                .orElseThrow(() -> new UsernameNotFoundException("User not found: "));

        return ResponseEntity.ok().body(userDataMapper.toUserResponse(user, userProfile));
    }

//    @GetMapping("/photos")
//    public ResponseEntity<List<String>> getPhotos(@RequestHeader("Authorization") String authHeader) {
//        Optional<User> user = getUser(authHeader);
//
//    }

//    @GetMapping("/friends")
//    private ResponseEntity<List<User>> getFriends(@RequestHeader("Authorization") String authHeader) {
//        String username = userService.getUsernameFromToken(authHeader);
//
//        List<User> friends = friendService.findAllFriends(username);
//        return ResponseEntity.ok().body(friends);
//    }
}

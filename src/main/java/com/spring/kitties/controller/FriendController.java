package com.spring.kitties.controller;

import com.spring.kitties.controller.mapper.FriendMapper;
import com.spring.kitties.controller.mapper.UserDataMapper;
import com.spring.kitties.controller.payload.FriendResponse;
import com.spring.kitties.controller.payload.FullFriendResponse;
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
import org.springframework.web.bind.annotation.*;
import com.spring.kitties.service.JwtService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequestMapping("/api/v1/friends")
@RestController
public class FriendController {

    @Autowired
    private UserService userService;
    @Autowired
    private FriendService friendService;
    @Autowired
    private FriendMapper friendMapper;

    @GetMapping
    private ResponseEntity<List<FriendResponse>> getFriends(@RequestHeader("Authorization") String authHeader) {
        String userId = userService.getUserIdFromToken(authHeader);

        List<FriendResponse> friends = friendService.findAllFriends(userId).stream()
                .map(entity -> friendMapper.toFriend(entity))
                .toList();
        return ResponseEntity.ok().body(friends);
    }

    @GetMapping("/candidates")
    private ResponseEntity<List<FullFriendResponse>> getCandidates(@RequestHeader("Authorization") String authHeader) {
        String userId = userService.getUserIdFromToken(authHeader);

        List<FullFriendResponse> nonFriends = friendService.findAllNonFriends(userId)
                .stream()
                .map(entity -> friendMapper.toFullUser(entity))
                .toList();

        return ResponseEntity.ok().body(nonFriends);
    }

    @DeleteMapping("/{friendId}")
    private ResponseEntity<String> deleteFriend(@RequestHeader("Authorization") String authHeader, @PathVariable("friendId") String friendId) {
        String userId = userService.getUserIdFromToken(authHeader);

        friendService.deleteFriend(userId, friendId);
        return ResponseEntity.ok().body("Friend deleted successfully");
    }

    @PostMapping("/{friendId}")
    public ResponseEntity<String> addFriend(@RequestHeader("Authorization") String authHeader, @PathVariable("friendId") String friendId) {
        String userId = userService.getUserIdFromToken(authHeader);

        friendService.addFriend(userId, friendId);
        return ResponseEntity.ok("Friend added successfully");
    }
}


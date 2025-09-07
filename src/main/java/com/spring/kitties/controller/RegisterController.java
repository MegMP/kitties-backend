package com.spring.kitties.controller;

import com.spring.kitties.controller.mapper.UserProfileRegisterMapper;
import com.spring.kitties.controller.mapper.UserRegisterMapper;
import com.spring.kitties.exception.DuplicateEmailException;
import com.spring.kitties.exception.DuplicateUsernameException;
import com.spring.kitties.controller.payload.RegisterRequest;
import com.spring.kitties.model.User;
import com.spring.kitties.model.UserProfile;
import com.spring.kitties.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/register")
public class RegisterController {

    @Autowired
    UserService userService;
    @Autowired
    UserRegisterMapper  userRegisterMapper;
    @Autowired
    UserProfileRegisterMapper userProfileRegisterMapper;

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request)
    {
        try {
            userService.addUser(userRegisterMapper.toUser(request), userProfileRegisterMapper.toUserProfile(request));
            return ResponseEntity.ok().build();
        } catch (DuplicateEmailException e) {
            Map<String, String> error = new HashMap<>();
            error.put("email", "Email is already in use");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
        } catch (DuplicateUsernameException e) {
            Map<String, String> error = new HashMap<>();
            error.put("username", "Username is already in use");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
        }
    }
}
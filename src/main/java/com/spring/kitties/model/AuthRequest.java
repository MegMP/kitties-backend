package com.spring.kitties.model;

public class AuthRequest {
    private String username;
    private String password;

    AuthRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

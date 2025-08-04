package com.spring.kitties.controller.payload;

public class RegisterRequest {
    private String username;
    private String firstname;
    private String lastname;
    private String password;
    private String email;
    private String city;

    RegisterRequest(String username, String firstname, String lastname, String password, String email, String city) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getCity() {
        return city;
    }
}

package com.spring.kitties.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    private Long id;

    private String lastname;
    private String firstname;
    private String email;

    private String username;
    private String password;
    private String city;

    public User() {

    }

    public User(String firstname, String lastname, String username, String email, String password, String city) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.city = city;
    }

    public Long getId() { return id; }

    public String getFirstname() { return firstname; }
    public void setFirstname(String firstname) { this.firstname = firstname; }

    public String getLastname() { return lastname; }
    public void setLastname(String lastname) { this.lastname = lastname;}

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email;}

    public String getUsername() { return username; }
    public void setUsername(String nickname) { this.username = nickname; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
}

package com.spring.kitties.model;

import jakarta.persistence.*;
import com.spring.kitties.model.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Builder
@Getter
public class UserProfileEntity {
    @Id
    private String userId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
    public void setUser(UserEntity user) {
        this.userEntity = user;
    }

    @Setter
    private String lastname;
    @Setter
    private String firstname;
    @Setter
    private String email;
    @Setter
    private String city;

    public UserProfileEntity() {}

    public UserProfileEntity(String firstname, String lastname, String email, String city) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.city = city;
    }
}

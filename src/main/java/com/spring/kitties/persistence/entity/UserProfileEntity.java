package com.spring.kitties.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "user_profile")
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
}

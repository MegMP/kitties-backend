package com.spring.kitties.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "CHAR(36)")
//    @NonNull
    private String userId;
    @Setter
    @NonNull
    private String username;
    @Setter
    @NonNull
    private String password;
}

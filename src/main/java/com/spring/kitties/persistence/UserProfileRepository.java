package com.spring.kitties.persistence;

import com.spring.kitties.persistence.entity.UserEntity;
import com.spring.kitties.persistence.entity.UserProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfileEntity, Long> {
    Optional<UserProfileEntity> findByUserId(String id);

    boolean existsByEmail(String email);
}

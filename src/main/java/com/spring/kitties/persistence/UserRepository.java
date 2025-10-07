package com.spring.kitties.persistence;

import com.spring.kitties.persistence.entity.UserEntity;
import com.spring.kitties.persistence.entity.UserProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByUserId(String id);

    boolean existsByUsername(String username);

    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN TRUE ELSE FALSE END " +
            "FROM FriendEntity f WHERE f.friendKey.friendId = :userId AND f.accepted = false")
    boolean isUnacceptedFriend(String userId);

    @Query("SELECT u FROM UserEntity u WHERE u.userId != :userId AND u.userId NOT IN (SELECT f.friendKey.friendId FROM FriendEntity f WHERE f.friendKey.userId = :userId)")
    List<UserEntity> findAllNonFriends(String userId);

    @Transactional
    @Modifying
    @Query("UPDATE UserEntity u SET u.username = :username WHERE u.userId = :userId")
    void updateUsername(@Param("userId") String userId, @Param("username") String username);

    @Transactional
    @Modifying
    @Query("UPDATE UserEntity u SET u.password = :password WHERE u.userId = :userId")
    void updatePassword(@Param("userId") String userId, @Param("password") String password);
}

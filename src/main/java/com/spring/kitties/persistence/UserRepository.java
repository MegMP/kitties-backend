package com.spring.kitties.persistence;

import com.spring.kitties.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

//    @Query("select * from User where username = :username")
    Optional<UserEntity> findByUsername(String username);
    boolean existsByUsername(String username);

    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN TRUE ELSE FALSE END " +
            "FROM FriendEntity f WHERE f.friendKey.friendId = :userId AND f.accepted = false")
    boolean isUnacceptedFriend(String userId);

    @Query("SELECT u FROM UserEntity u WHERE u.userId != :userId AND u.userId NOT IN (SELECT f.friendKey.friendId FROM FriendEntity f WHERE f.friendKey.userId = :userId)")
    List<UserEntity> findAllNonFriends(String userId);
}

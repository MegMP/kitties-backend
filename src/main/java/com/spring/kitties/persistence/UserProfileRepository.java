package com.spring.kitties.persistence;

import com.spring.kitties.persistence.entity.UserEntity;
import com.spring.kitties.persistence.entity.UserProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfileEntity, Long> {
    Optional<UserProfileEntity> findByUserId(String id);

    boolean existsByEmail(String email);

    @Query("SELECT up FROM UserProfileEntity up JOIN FriendEntity f ON up.userId=f.friendKey.friendId WHERE f.friendKey.userId=:userId AND f.accepted=true")
    List<UserProfileEntity> findAllFriends(String userId);
}

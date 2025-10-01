package com.spring.kitties.persistence;

import com.spring.kitties.persistence.entity.FriendEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface FriendRepository extends JpaRepository<FriendEntity, Long> {
    @Transactional
    @Modifying
    @Query("DELETE FROM FriendEntity f " +
            "WHERE (f.friendKey.userId = :userId AND f.friendKey.friendId = :friendId) " +
            "   OR (f.friendKey.userId = :friendId AND f.friendKey.friendId = :userId)")
    void deleteFriend(String userId, String friendId);

    @Transactional
    @Modifying
    @Query("UPDATE FriendEntity f SET f.accepted = true " +
            "WHERE f.friendKey.userId = :friendId AND f.friendKey.friendId = :userId")
    void acceptFriend(String userId, String friendId);
}

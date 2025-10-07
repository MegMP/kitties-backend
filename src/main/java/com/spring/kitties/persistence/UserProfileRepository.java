package com.spring.kitties.persistence;

import com.spring.kitties.model.UserProfile;
import com.spring.kitties.persistence.entity.UserEntity;
import com.spring.kitties.persistence.entity.UserProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfileEntity, Long> {
    Optional<UserProfileEntity> findByUserId(String id);

    boolean existsByEmail(String email);

    @Query("SELECT up FROM UserProfileEntity up JOIN FriendEntity f ON up.userId=f.friendKey.friendId WHERE f.friendKey.userId=:userId AND f.accepted=true")
    List<UserProfileEntity> findAllFriends(String userId);

    @Transactional
    @Modifying
    @Query("UPDATE UserProfileEntity up SET " +
            "up.firstname = :firstname," +
            "up.lastname = :lastname," +
            "up.email = :email," +
            "up.city = :city WHERE up.userId = :userId")
    void updateUserProfile(@Param("userId") String userId,
                         @Param("firstname") String firstname,
                         @Param("lastname") String lastname,
                         @Param("email") String email,
                         @Param("city") String city);
}

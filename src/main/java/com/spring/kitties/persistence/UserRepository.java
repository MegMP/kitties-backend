package com.spring.kitties.persistence;
import com.spring.kitties.model.User;
import com.spring.kitties.model.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

//    @Query("select * from User where username = :username")
    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findByProfileEmail(String email);

}

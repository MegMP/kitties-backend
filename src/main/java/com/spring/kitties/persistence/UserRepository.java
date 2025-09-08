package com.spring.kitties.persistence;

import com.spring.kitties.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

//    @Query("select * from User where username = :username")
    Optional<UserEntity> findByUsername(String username);
    boolean existsByUsername(String username);


}

package com.blog.repositories;

import com.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);

    User findByUserId(String userId);
    @Query(value = "select u from users u ")
    List<User> allUsers();

    boolean existsUserByEmail(String email);
    //Optional<User> findByUserName(String email);
}

package com.workintech.s19challenge.repository;

import com.workintech.s19challenge.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    //emailden kullanıcıyı bulalım
    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findUserByEmail(String email);

    //userNameden kullanıcıyı bulalım
    @Query("SELECT u FROM User u WHERE u.userName = :userName")
    Optional<User> findUserByUsername(String userName);
}

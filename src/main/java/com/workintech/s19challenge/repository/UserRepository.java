package com.workintech.s19challenge.repository;

import com.workintech.s19challenge.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

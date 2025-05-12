package com.workintech.s19challenge.service;

import com.workintech.s19challenge.entity.user.User;

import java.util.List;

public interface UserService {

    List<User> findAll();
    User findById(long id);
    User save(User user);
    User delete(long id);
}

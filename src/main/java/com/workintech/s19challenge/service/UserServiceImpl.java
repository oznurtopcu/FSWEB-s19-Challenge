package com.workintech.s19challenge.service;

import com.workintech.s19challenge.entity.User;
import com.workintech.s19challenge.exceptions.ApiException;
import com.workintech.s19challenge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.List;

public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new ApiException("User not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User delete(long id) {
        User foundUser = findById(id);
        userRepository.delete(foundUser);
        return foundUser;
    }
}

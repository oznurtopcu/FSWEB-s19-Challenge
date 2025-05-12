package com.workintech.s19challenge.service;

import com.workintech.s19challenge.entity.Tweet;

import java.util.List;

public interface TweetService {

    List<Tweet> findAll();
    Tweet findById(long id);
    Tweet save(Tweet tweet);
    Tweet update(long id, Tweet tweet);
    Tweet delete(long id);
    List<Tweet> findByUserId(long id);
}

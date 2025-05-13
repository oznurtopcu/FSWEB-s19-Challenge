package com.workintech.s19challenge.service;

import com.workintech.s19challenge.entity.Tweet;

import java.util.List;

public interface TweetService {

    List<Tweet> findAll();
    Tweet findById(long id);
    List<Tweet> findByUserId(long id);
    Tweet save(Tweet tweet);
    Tweet create(String content, long user_id);
    Tweet update(long id, String content, long user_id);
    Tweet delete(long id, long user_id);


}

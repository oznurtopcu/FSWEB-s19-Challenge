package com.workintech.s19challenge.service;

import com.workintech.s19challenge.entity.Like;
import com.workintech.s19challenge.entity.Tweet;
import com.workintech.s19challenge.entity.user.User;

import java.util.List;

public interface LikeService {

    List<Like> findAll();
    Like findById(long id);
    List<Like> findByUserId(long user_id);
    List<Like> findByTweetId(long tweet_id);
    Like save(Like like);
    Like delete(Like like);
    Like like(long tweet_id, long user_id);
    Like dislike(long tweet_id, long user_id);
}

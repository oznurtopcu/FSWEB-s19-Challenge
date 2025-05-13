package com.workintech.s19challenge.service;

import com.workintech.s19challenge.entity.Comment;

import java.util.List;

public interface CommentService {

//    List<Comment> findAll();
//    List<Comment> findByUserId(long id);
    Comment findById(long id);
    Comment save(Comment comment);
    Comment create(long tweet_id, String content, long user_id);
    Comment update(long id, String content, long user_id);
    Comment delete(long id, long user_id);

}

package com.workintech.s19challenge.controller;

import com.workintech.s19challenge.dto.CommentRequest;
import com.workintech.s19challenge.entity.Comment;
import com.workintech.s19challenge.entity.user.User;
import com.workintech.s19challenge.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public Comment create(@RequestBody CommentRequest commentRequest, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return commentService.create(commentRequest.tweet_id(), commentRequest.content(), user.getId());
    }

    @PutMapping("/{id}")
    public Comment update(@PathVariable long id, @RequestBody String content, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return commentService.update(id,content, user.getId());
    }

    @DeleteMapping("/{id}")
    public Comment delete(@PathVariable long id, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return commentService.delete(id, user.getId());
    }
}

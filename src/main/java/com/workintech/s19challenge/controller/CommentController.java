package com.workintech.s19challenge.controller;

import com.workintech.s19challenge.dto.CommentCreateRequest;
import com.workintech.s19challenge.dto.CommentUpdateRequest;
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

    @PostMapping("/")
    public Comment create(@RequestBody CommentCreateRequest commentCreateRequest, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return commentService.create(commentCreateRequest.tweet_id(), commentCreateRequest.content(), user.getId());
    }

    @PutMapping("/{id}")
    public Comment update(@PathVariable long id, @RequestBody CommentUpdateRequest commentUpdateRequest, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return commentService.update(id,commentUpdateRequest.content(), user.getId());
    }

    @DeleteMapping("/{id}")
    public Comment delete(@PathVariable long id, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return commentService.delete(id, user);
    }
}

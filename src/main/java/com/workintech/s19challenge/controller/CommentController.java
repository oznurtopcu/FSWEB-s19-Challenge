package com.workintech.s19challenge.controller;

import com.workintech.s19challenge.entity.Comment;
import com.workintech.s19challenge.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Comment create() {

    }

    @PutMapping("/{id}")
    public Comment update(@PathVariable long id) {

    }

    @DeleteMapping("/{id}")
    public Comment delete(@PathVariable long id) {

    }
}

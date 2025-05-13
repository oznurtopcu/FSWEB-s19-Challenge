package com.workintech.s19challenge.service;

import com.workintech.s19challenge.entity.Comment;
import com.workintech.s19challenge.entity.user.User;
import com.workintech.s19challenge.exceptions.ApiException;
import com.workintech.s19challenge.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService{

    private CommentRepository commentRepository;
    private UserService userService;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, UserService userService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
    }

    @Override
    public Comment findById(long id) {
        return commentRepository.findById(id).orElseThrow(() -> new ApiException("Comment not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment create(long tweet_id, String content, long user_id) {
        User foundUser = userService.findById(user_id);
        Comment comment = new Comment();
        comment.setComment(content);
        comment.setUser(foundUser);
        foundUser.addComment(comment);
        return save(comment);
    }

    @Override
    public Comment update(long id, String content, long user_id) {
        Comment foundComment = findById(id);

        if(!foundComment.getUser().getId().equals(user_id)) {
            throw new ApiException("No authorisation to update the tweet!", HttpStatus.FORBIDDEN);
        }

        if(content.trim().isEmpty()) {
            throw new ApiException("Comment content cannot be empty!", HttpStatus.BAD_REQUEST);
        }

        foundComment.setComment(content);
        return save(foundComment);
    }

    @Override
    public Comment delete(long id, long user_id) {
        Comment foundComment = findById(id);

        if(!foundComment.getUser().getId().equals(user_id) || !foundComment.getTweet().getUser().getId().equals(user_id)) {
            throw new ApiException("No authorisation to delete the tweet!", HttpStatus.FORBIDDEN);
        }
        commentRepository.delete(foundComment);
        return foundComment;
    }
}

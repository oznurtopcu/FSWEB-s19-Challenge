package com.workintech.s19challenge.service;

import com.workintech.s19challenge.entity.Like;
import com.workintech.s19challenge.entity.Tweet;
import com.workintech.s19challenge.entity.user.User;
import com.workintech.s19challenge.exceptions.ApiException;
import com.workintech.s19challenge.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeServiceImpl implements LikeService{

    private LikeRepository likeRepository;
    private TweetService tweetService;
    private UserService userService;

    @Autowired
    public LikeServiceImpl(LikeRepository likeRepository, TweetService tweetService, UserService userService) {
        this.likeRepository = likeRepository;
        this.tweetService = tweetService;
        this.userService = userService;
    }


    @Override
    public List<Like> findAll() {
        return likeRepository.findAll();
    }

    @Override
    public Like findById(long id) {
        return likeRepository.findById(id).orElseThrow(() -> new ApiException("Like not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public List<Like> findByUserId(long user_id) {
        return likeRepository.findByUserId(user_id);
    }

    @Override
    public List<Like> findByTweetId(long tweet_id) {
        return likeRepository.findByTweetId(tweet_id);
    }

    @Override
    public Like save(Like like) {
        return likeRepository.save(like);
    }

    @Override
    public Like delete(Like like) {
        likeRepository.delete(like);
        return like;
    }

    @Override
    public Like like(long tweet_id, long user_id) {
        User foundUser = userService.findById(user_id);
        Tweet foundTweet = tweetService.findById(tweet_id);
        Like foundLike = likeRepository.findIsLiked(tweet_id, foundUser.getId());
        if(foundLike != null) {
            throw new ApiException("Tweet already liked!", HttpStatus.CONFLICT);
        }

        Like like = new Like();
        like.setTweet(foundTweet);
        like.setUser(foundUser);
        foundTweet.addLike(like);
        foundUser.addLike(like);

        return save(like);
    }

    @Override
    public Like dislike(long tweet_id, long user_id) {
        User foundUser = userService.findById(user_id);
        Tweet foundTweet = tweetService.findById(tweet_id);
        Like foundLike = likeRepository.findIsLiked(tweet_id, foundUser.getId());
        if(foundLike == null) {
            throw new ApiException("Tweet not liked, cannot remove!", HttpStatus.BAD_REQUEST);
        }

        foundUser.getLikes().remove(foundLike);
        foundTweet.getLikes().remove(foundLike);

        return delete(foundLike);
    }
}

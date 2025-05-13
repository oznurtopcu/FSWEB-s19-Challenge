package com.workintech.s19challenge.service;

import com.workintech.s19challenge.entity.Tweet;
import com.workintech.s19challenge.entity.user.User;
import com.workintech.s19challenge.exceptions.ApiException;
import com.workintech.s19challenge.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TweetServiceImpl implements TweetService{

    private TweetRepository tweetRepository;
    private UserService userService;

    @Autowired
    public TweetServiceImpl(TweetRepository tweetRepository, UserService userService) {
        this.tweetRepository = tweetRepository;
        this.userService = userService;
    }

    @Override
    public List<Tweet> findAll() {
        return tweetRepository.findAll().stream().collect(Collectors.toUnmodifiableList());
    }

    @Override
    public Tweet findById(long id) {
        return tweetRepository.findById(id).orElseThrow(() -> new ApiException("Tweet not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public Tweet save(Tweet tweet) {
        return tweetRepository.save(tweet);
    }


    @Override
    public Tweet update(long id, String content, long user_id) {
        Tweet foundTweet = findById(id);
        if(!foundTweet.getUser().getId().equals(user_id)) {
            throw new ApiException("No authorisation to update the tweet!", HttpStatus.FORBIDDEN);
        }

        if(content.trim().isEmpty()) {
            throw new ApiException("Tweet content cannot be empty!", HttpStatus.BAD_REQUEST);
        }

        foundTweet.setContent(content);
        return tweetRepository.save(foundTweet);
    }

    @Override
    public Tweet delete(long id, long user_id) {
        Tweet foundTweet = findById(id);

        if(!foundTweet.getUser().getId().equals(user_id)) {
            throw new ApiException("No authorisation to delete the tweet!", HttpStatus.FORBIDDEN);
        }

        tweetRepository.delete(foundTweet);
        return foundTweet;
    }

    @Override
    public List<Tweet> findByUserId(long id) {
        //önce user bul
        User foundUser = userService.findById(id);
        //sonra user'ın tweetlerini getir.
        return foundUser.getTweets().stream().collect(Collectors.toUnmodifiableList());
    }

    @Override
    public Tweet create(String content, long user_id) {
        User foundUser = userService.findById(user_id);

        Tweet tweet = new Tweet();
        tweet.setContent(content);
        tweet.setUser(foundUser);
        foundUser.addTweet(tweet);
        return tweetRepository.save(tweet);
    }

}

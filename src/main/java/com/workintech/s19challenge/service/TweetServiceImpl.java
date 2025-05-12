package com.workintech.s19challenge.service;

import com.workintech.s19challenge.entity.Tweet;
import com.workintech.s19challenge.entity.User;
import com.workintech.s19challenge.exceptions.ApiException;
import com.workintech.s19challenge.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
    public Tweet update(long id, Tweet tweet) {
        //id kullanarak güncellenmesi istenen tweeti bulduk
        Tweet foundTweet = findById(id);
        //yeni tweet'in id'sini bulduğumuz id'ye eşitledik
        tweet.setId(foundTweet.getId());
        //Tweet'i kaydettik, id aynı olduğu için eski bilginin üzerine yazıldı
        return save(tweet);
    }

    @Override
    public Tweet delete(long id) {
        Tweet foundTweet = findById(id);
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
}

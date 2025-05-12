package com.workintech.s19challenge.controller;

import com.workintech.s19challenge.entity.Tweet;
import com.workintech.s19challenge.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tweet")
public class TweetController {

    private TweetService tweetService;

    @Autowired
    public TweetController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @GetMapping("/findByUserId/{id}")
    public List<Tweet> findByUserId(@PathVariable long id) {
        return tweetService.findByUserId(id);
    }

    @GetMapping("/findById/{id}")
    public Tweet findById(@PathVariable long id) {
        return tweetService.findById(id);
    }

    @PutMapping("/{id}")
    public Tweet update(@PathVariable long id, @RequestBody Tweet tweet) {
        return tweetService.update(id, tweet);
    }

    @DeleteMapping("/{id}")
    public Tweet delete(@PathVariable long id) {
        return tweetService.delete(id);
    }

}

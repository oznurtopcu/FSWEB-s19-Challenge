package com.workintech.s19challenge.controller;

import com.workintech.s19challenge.dto.TweetRequest;
import com.workintech.s19challenge.entity.Tweet;
import com.workintech.s19challenge.entity.user.User;
import com.workintech.s19challenge.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/tweet")
public class TweetController {

    private TweetService tweetService;

    @Autowired
    public TweetController(TweetService tweetService) {
        this.tweetService = tweetService;
    }


    @GetMapping("/")
    public List<Tweet> welcomeTwitter() {
        return tweetService.findAll();
    }

    @GetMapping("/findByUserId/{id}")
    public List<Tweet> findByUserId(@PathVariable long id) {
        return tweetService.findByUserId(id);
    }

    @GetMapping("/findById/{id}")
    public Tweet findById(@PathVariable long id) {
        return tweetService.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public Tweet create(@RequestBody TweetRequest tweetRequest, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return tweetService.create(tweetRequest.content(), user.getId());
    }

    @PutMapping("/{id}")
    public Tweet update(@PathVariable long id, @RequestBody TweetRequest tweetRequest, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return tweetService.update(id, tweetRequest.content(), user.getId());
    }

    @DeleteMapping("/{id}")
    public Tweet delete(@PathVariable long id, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return tweetService.delete(id, user.getId());
    }



}

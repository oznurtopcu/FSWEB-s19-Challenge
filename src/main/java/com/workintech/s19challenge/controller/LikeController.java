package com.workintech.s19challenge.controller;

import com.workintech.s19challenge.dto.LikeRequest;
import com.workintech.s19challenge.entity.Like;
import com.workintech.s19challenge.entity.user.User;
import com.workintech.s19challenge.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LikeController {

    private LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/like")
    public Like like(@RequestBody LikeRequest likeRequest, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return likeService.like(likeRequest.tweet_id(),user.getId());
    }

    @PostMapping("/dislike")
    public Like dislike(@RequestBody LikeRequest likeRequest, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return likeService.dislike(likeRequest.tweet_id(),user.getId());
    }
}

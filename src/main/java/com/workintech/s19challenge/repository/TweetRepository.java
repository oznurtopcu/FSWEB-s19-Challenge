package com.workintech.s19challenge.repository;

import com.workintech.s19challenge.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TweetRepository extends JpaRepository<Tweet, Long> {
}

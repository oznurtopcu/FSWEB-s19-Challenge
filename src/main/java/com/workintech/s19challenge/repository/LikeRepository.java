package com.workintech.s19challenge.repository;

import com.workintech.s19challenge.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {

    @Query("SELECT l FROM Like l WHERE l.tweet.id = :tweet_id")
    List<Like> findByTweetId(long tweet_id);

    @Query("SELECT l FROM Like l WHERE l.user.id = :user_id")
    List<Like> findByUserId(long user_id);

    @Query("SELECT l FROM Like l WHERE l.tweet.id = :tweet_id AND l.user.id = :user_id")
    Like findIsLiked(@Param("tweet_id") long tweet_id, @Param("user_id") long user_id);
}

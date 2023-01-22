package com.tweteroo.api.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tweteroo.api.model.Tweet;
import com.tweteroo.api.projection.TweetProjection;

public interface TweetRepository extends JpaRepository<Tweet, Long> {
  
  @Query(
    nativeQuery = true, 
    value = "SELECT u.username, u.avatar, t.tweet "
          + "FROM tb_tweets t "
          + "INNER JOIN tb_users u ON (t.user_id = u.id) ORDER BY t.id DESC",
    countQuery = "SELECT count(*) FROM tb_tweets t INNER JOIN tb_users u ON (t.user_id = u.id)")
    List<TweetProjection> findAllTweets(Pageable pageable);
          
 
  @Query(
    nativeQuery = true,
    value = "SELECT u.username, u.avatar, t.tweet "
          + "FROM tb_tweets t "
          + "INNER JOIN tb_users u ON (t.user_id = u.id) "
          + "WHERE UPPER(u.username) LIKE UPPER((:username)) "
          + "ORDER BY t.id DESC")
  List<TweetProjection> findByUsername(String username);

}

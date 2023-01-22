package com.tweteroo.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tweteroo.api.dto.TweetDTO;
import com.tweteroo.api.model.Tweet;
import com.tweteroo.api.model.User;
import com.tweteroo.api.projection.TweetProjection;
import com.tweteroo.api.repository.TweetRepository;

@Service
public class TweetService {
  
  @Autowired
  private TweetRepository repository;

  @Autowired
  private UserService userRepository;

  public void create(TweetDTO tweetDTO) {
    User user = userRepository.findByUsername(tweetDTO.username());
    if (user == null) {
      // TODO 
    }
    repository.save(new Tweet(user, tweetDTO.tweet()));
  }

  public List<TweetProjection> findAll(Pageable page) {
    return repository.findAllTweets(page);
  }

  public List<TweetProjection> findByUsername(String username) {
    return repository.findByUsername(username.trim());
  }

}

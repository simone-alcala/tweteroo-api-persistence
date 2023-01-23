package com.tweteroo.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tweteroo.api.dto.TweetDTO;
import com.tweteroo.api.model.Tweet;
import com.tweteroo.api.model.User;
import com.tweteroo.api.projection.TweetProjection;
import com.tweteroo.api.repository.TweetRepository;
import com.tweteroo.api.service.exceptions.EntityNotFoundException;


@Service
public class TweetService {
  
  @Autowired
  private TweetRepository repository;

  @Autowired
  private UserService userService;

  public void create(TweetDTO tweetDTO) {
    User user = userService.findByUsername(tweetDTO.username());

    if (user == null) {
      throw new EntityNotFoundException("Username not found: " + tweetDTO.username());
    }

    repository.save(new Tweet(user, tweetDTO.tweet()));
  }

  public Page<TweetProjection> findAll(Pageable page) {
    return repository.findAllTweets(page);
  }

  public List<TweetProjection> findByUsername(String username) {
    return repository.findByUsername(username.trim());
  }

}

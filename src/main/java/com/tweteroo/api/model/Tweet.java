package com.tweteroo.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_tweets")
public class Tweet {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String tweet;

  @ManyToOne(optional = false)
  @JoinColumn(name="user_id", nullable = false)
  @JsonBackReference
  private User user;
 
  public Tweet() {
  }

  public Tweet(User user, String tweet) {
    this.user = user;
    this.tweet = tweet.trim();
  }

  public Long getId() {
    return id;
  }

  public String getTweet() {
    return tweet;
  }

  public void setTweet(String tweet) {
    this.tweet = tweet.trim();
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
  
}

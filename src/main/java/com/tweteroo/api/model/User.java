package com.tweteroo.api.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tweteroo.api.dto.UserDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String username;
  
  @Column(nullable = false)
  private String avatar;

  @OneToMany(mappedBy = "user")
  @JsonManagedReference
  private List<Tweet> tweets = new ArrayList<>();
  
  public User() {
  }

  public User(String username, String avatar) {
    this.username = username.trim().toLowerCase();
    this.avatar = avatar.trim();
  }

  public User(UserDTO userDTO) {
    this.username = userDTO.username().trim().toLowerCase();
    this.avatar = userDTO.avatar().trim();
  }

  public Long getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username.trim().toLowerCase();
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar.trim();
  }

  public List<Tweet> getTweets() {
    return tweets;
  }

  public void addTweet(Tweet tweet) {
    tweets.add(tweet);
  }

  public void removeTweet(Tweet tweet) {
    tweets.remove(tweet);
  }
  
}

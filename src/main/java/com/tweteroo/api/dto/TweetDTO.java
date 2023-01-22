package com.tweteroo.api.dto;

import jakarta.validation.constraints.NotBlank;

public record TweetDTO (
  @NotBlank
  String username,

  @NotBlank
  String tweet) {
  
}

package com.tweteroo.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserDTO (
  @NotBlank
  String username,

  @NotBlank
  @Pattern(regexp = "^(?i)(https?:/{2})([a-z0-9+&@#/%?=~_!:,.;-]+)(.[png|bmp|jpe?g|gif])$")
  String avatar) {
}

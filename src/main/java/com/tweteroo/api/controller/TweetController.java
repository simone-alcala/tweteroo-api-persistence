package com.tweteroo.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweteroo.api.dto.TweetDTO;
import com.tweteroo.api.projection.TweetProjection;
import com.tweteroo.api.service.TweetService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/tweets")
public class TweetController {
  
  @Autowired
  private TweetService service;

  @PostMapping
  public ResponseEntity<String> create(@RequestBody @Valid TweetDTO req) {
    service.create(req);
    return ResponseEntity.ok(null);
  }

  @GetMapping
  public ResponseEntity<Page<TweetProjection>> findAll(@PageableDefault(page = 0, size = 5) Pageable page) {
    return ResponseEntity.ok().body(service.findAll(page));
  }

  @GetMapping("/{username}")
  public ResponseEntity<List<TweetProjection>> findByUsername(@PathVariable("username") String username) {
    return ResponseEntity.ok().body(service.findByUsername(username));
  }
  
}

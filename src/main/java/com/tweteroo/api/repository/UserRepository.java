package com.tweteroo.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tweteroo.api.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
  
  User findByUsernameIgnoreCase(String username);

}

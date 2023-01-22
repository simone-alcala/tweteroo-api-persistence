package com.tweteroo.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweteroo.api.dto.UserDTO;
import com.tweteroo.api.model.User;
import com.tweteroo.api.repository.UserRepository;

@Service
public class UserService {
 
  @Autowired
  private UserRepository repository;

  public void create(UserDTO userDTO) {

    User user = this.findByUsername(userDTO.username());
    
    if (user == null) {
      repository.save(new User(userDTO));
    } else {
      user.setAvatar(userDTO.avatar());
      user.setUsername(userDTO.username());
      repository.save(user);
    }
    
  }

  public List<User> findAll() {
      return repository.findAll();
  }

  protected User findByUsername(String username) {
    return repository.findByUsernameIgnoreCase(username.trim());
  }

}

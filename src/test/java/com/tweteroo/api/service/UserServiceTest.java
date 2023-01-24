package com.tweteroo.api.service;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.tweteroo.api.ApplicationConfigTest;
import com.tweteroo.api.dto.UserDTO;
import com.tweteroo.api.model.User;
import com.tweteroo.api.repository.UserRepository;

@DisplayName("UserServiceTest")
public class UserServiceTest extends ApplicationConfigTest {
  
  @MockBean
  private UserRepository repository;

  @Autowired
  private UserService userService;

  @Test
  @DisplayName("Should create a user")
  public void shouldCreateUser() {

    Long id = 1L;
    String username = "user-mock";
    String avatar = "https://testing.png";

    UserDTO userDTO = Mockito.mock(UserDTO.class);
    Mockito.when(userDTO.username()).thenReturn(username);
    Mockito.when(userDTO.avatar()).thenReturn(avatar);
    
    Mockito.when(repository.findByUsernameIgnoreCase(userDTO.username())).thenReturn(null);

    User user = Mockito.mock(User.class);
    Mockito.when(user.getId()).thenReturn(id);
    Mockito.when(user.getUsername()).thenReturn(username);
    Mockito.when(user.getAvatar()).thenReturn(avatar);

    Mockito.when(repository.save(new User(userDTO))).thenReturn(user);

    userService.create(userDTO);
    Mockito.verify(repository, Mockito
      .times(1))
      .save(ArgumentMatchers.any(User.class));
  }

  @Test
  @DisplayName("Should update a user")
  public void shouldUpdateUser() {
    Long id = 1L;
    String username = "user-mock";
    String avatar = "https://testing.png";

    UserDTO userDTO = Mockito.mock(UserDTO.class);
    Mockito.when(userDTO.username()).thenReturn(username);
    Mockito.when(userDTO.avatar()).thenReturn(avatar);

    User user = Mockito.mock(User.class);
    Mockito.when(user.getId()).thenReturn(id);
    Mockito.when(user.getUsername()).thenReturn(username);
    Mockito.when(user.getAvatar()).thenReturn(avatar);

    Mockito.when(repository.findByUsernameIgnoreCase(userDTO.username())).thenReturn(user);

    Mockito.when(repository.save(new User(userDTO))).thenReturn(user);

    userService.create(userDTO);
    Mockito.verify(repository, Mockito
      .times(1))
      .save(ArgumentMatchers.any(User.class));
  }

  @Test
  @DisplayName("Should find all users")
  public void shouldFindAllUsers() {
    Long id = 1L;
    String username = "user-mock";
    String avatar = "https://testing.png";
    
    User user = Mockito.mock(User.class);
    Mockito.when(user.getId()).thenReturn(id);
    Mockito.when(user.getUsername()).thenReturn(username);
    Mockito.when(user.getAvatar()).thenReturn(avatar);

    List<User> users = new ArrayList<>();
    users.add(user);

    Mockito.when(repository.findAll()).thenReturn(users);

    userService.findAll();   
    Mockito.verify(repository, Mockito.times(1)).findAll();
    
    assertEquals(users.size(), repository.findAll().size());
  }

  @Test
  @DisplayName("Should find a user by its username")
  public void shouldFindUserByUsername() {
    Long id = 1L;
    String username = "user-mock";
    String avatar = "https://testing.png";
    
    User user = Mockito.mock(User.class);
    Mockito.when(user.getId()).thenReturn(id);
    Mockito.when(user.getUsername()).thenReturn(username);
    Mockito.when(user.getAvatar()).thenReturn(avatar);

    Mockito.when(repository.findByUsernameIgnoreCase(username)).thenReturn(user);

    User userFound = userService.findByUsername(username);   
    Mockito.verify(repository, 
                Mockito.times(1))
                .findByUsernameIgnoreCase(ArgumentMatchers.anyString());
    
    assertEquals(user, userFound);
  }
}

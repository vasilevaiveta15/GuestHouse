package com.example.guesthouse.users.daos;

import com.example.guesthouse.users.dtos.UserRegistrationRequest;
import com.example.guesthouse.users.entity.User;

import java.util.Optional;

public interface UserDao
{
  void createUser(UserRegistrationRequest userRequest);

  Optional<User> findByUsername(String username);

}

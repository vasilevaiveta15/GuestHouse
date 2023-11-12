package com.example.guesthouse.users.services;

import com.example.guesthouse.users.dtos.UserRegistrationRequest;
import com.example.guesthouse.users.dtos.UserRegistrationResponse;
import com.example.guesthouse.users.entity.User;

public interface UserService
{
  UserRegistrationResponse createUser(UserRegistrationRequest userRequest);

  User getCurrentLoggedUser();
}

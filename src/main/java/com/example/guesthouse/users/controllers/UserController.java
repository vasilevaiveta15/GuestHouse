package com.example.guesthouse.users.controllers;

import com.example.guesthouse.users.dtos.UserRegistrationRequest;
import com.example.guesthouse.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping("/api/v1")
public class UserController
{
  private final UserService userService;

  @Autowired
  public UserController(UserService userService)
  {
    this.userService = userService;
  }

  @PostMapping("/register/user")
  public ResponseEntity<?> registration(@RequestBody @Valid UserRegistrationRequest userRequest)
  {
    return ResponseEntity.ok(userService.createUser(userRequest));
  }
}

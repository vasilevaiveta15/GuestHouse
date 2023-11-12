package com.example.guesthouse.users.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/api/v1")
public class LoginController
{
  @PostMapping("/login")
  public ResponseEntity<?> login()
  {
    return ResponseEntity.ok("Successfully login!");
  }
}

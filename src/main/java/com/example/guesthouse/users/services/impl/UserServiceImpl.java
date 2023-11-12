package com.example.guesthouse.users.services.impl;

import com.example.guesthouse.exceptions.InvalidException;
import com.example.guesthouse.users.daos.UserDao;
import com.example.guesthouse.users.dtos.UserRegistrationRequest;
import com.example.guesthouse.users.dtos.UserRegistrationResponse;
import com.example.guesthouse.users.entity.User;
import com.example.guesthouse.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.guesthouse.messages.ExceptionMessages.*;

@Service
@Transactional
public class UserServiceImpl implements UserService
{
  private final UserDao         userDao;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public UserServiceImpl(UserDao userDao)
  {
    this.userDao = userDao;
    this.passwordEncoder = new BCryptPasswordEncoder();
  }

  @Override
  public UserRegistrationResponse createUser(UserRegistrationRequest userRequest)
  {
    if (!(userRequest.getRole().equals("OWNER")
        || userRequest.getRole().equals("RENTER"))) {
      throw new InvalidException(INVALID_ROLE);
    }
    if (userDao.findByUsername(userRequest.getUsername()).isPresent()) {
      throw new InvalidException(USERNAME_ALREADY_EXIST);
    }
    passwordValidation(userRequest.getPassword(), userRequest.getRepeatPassword());

    userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
    userDao.createUser(userRequest);
    return new UserRegistrationResponse(userRequest.getUsername(), userRequest.getRole());
  }

  @Override
  public User getCurrentLoggedUser()
  {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String principalUsername = "";
    if (principal instanceof UserDetails) {
      principalUsername = ((UserDetails) principal).getUsername();
    }
    return userDao.findByUsername(principalUsername).orElseThrow(() -> new InvalidException(INVALID_USERNAME));
  }

  private void passwordValidation(String password, String repeatPassword)
  {
    if (!(password.equals(repeatPassword))) {
      throw new InvalidException(PASSWORD_DONT_MATCH);
    }
    boolean containsUpperCaseLetter = false;
    boolean containsLowerCaseLetter = false;
    boolean containsSpecialSymbol = false;
    boolean containsDigit = false;
    boolean containsWhiteSpace = false;
    for (char c : password.toCharArray()) {
      if (Character.isUpperCase(c)) {
        containsUpperCaseLetter = true;
      }
      if (Character.isLowerCase(c)) {
        containsLowerCaseLetter = true;
      }
      if (!Character.isAlphabetic(c) && (!Character.isDigit(c)) && (!Character.isWhitespace(c))) {
        containsSpecialSymbol = true;
      }
      if (Character.isDigit(c)) {
        containsDigit = true;
      }
      if (Character.isWhitespace(c)) {
        containsWhiteSpace = true;
      }
    }
    if (!containsSpecialSymbol) {
      throw new InvalidException(PASSWORD_SHOULD_HAVE_SPECIAL_SYMBOL);
    }
    else if (!containsDigit) {
      throw new InvalidException(PASSWORD_SHOULD_HAVE_DIGIT);
    }
    else if (!containsUpperCaseLetter) {
      throw new InvalidException(PASSWORD_SHOULD_HAVE_UPPERCASE_LETTER);
    }
    else if (!containsLowerCaseLetter) {
      throw new InvalidException(PASSWORD_SHOULD_HAVE_LOWERCASE_LETTER);
    }
    else if (containsWhiteSpace) {
      throw new InvalidException(PASSWORD_CANT_HAVE_WHITESPACE);
    }
    else if (password.length() < 7) {
      throw new InvalidException(PASSWORD_SHOULD_BE_MAX_7_SYMBOLS);
    }
  }
}

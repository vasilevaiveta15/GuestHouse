package com.example.guesthouse.users.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import static com.example.guesthouse.messages.ExceptionMessages.INVALID_USERNAME;
import static com.example.guesthouse.messages.ExceptionMessages.MUST_NOT_BE_EMPTY;

public class UserRegistrationRequest
{
  @NotEmpty(message = MUST_NOT_BE_EMPTY)
  @Pattern(regexp = "^[A-Za-z0-9.\\-]+$", message = INVALID_USERNAME)
  private String username;

  @NotEmpty(message = MUST_NOT_BE_EMPTY)
  private String password;

  @NotEmpty(message = MUST_NOT_BE_EMPTY)
  private String repeatPassword;

  @NotEmpty(message = MUST_NOT_BE_EMPTY)
  private String role;

  public UserRegistrationRequest()
  {
  }

  public String getUsername()
  {
    return username;
  }

  public void setUsername(String username)
  {
    this.username = username;
  }

  public String getPassword()
  {
    return password;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  public String getRepeatPassword()
  {
    return repeatPassword;
  }

  public void setRepeatPassword(String repeatPassword)
  {
    this.repeatPassword = repeatPassword;
  }

  public String getRole()
  {
    return role;
  }

  public void setRole(String role)
  {
    this.role = role;
  }
}

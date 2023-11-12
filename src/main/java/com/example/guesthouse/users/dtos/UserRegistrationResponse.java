package com.example.guesthouse.users.dtos;

public class UserRegistrationResponse
{
  private   String    username;
  private String role;

  public UserRegistrationResponse(String username, String role)
  {
    setUsername(username);
    setRole(role);
  }

  public String getUsername()
  {
    return username;
  }

  public void setUsername(String username)
  {
    this.username = username;
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

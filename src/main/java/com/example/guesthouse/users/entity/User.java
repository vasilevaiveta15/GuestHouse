package com.example.guesthouse.users.entity;

import org.springframework.data.annotation.Transient;
import org.springframework.security.core.GrantedAuthority;

public class User implements GrantedAuthority
{
  private Long   userId;
  private String username;
  private String password;
  private Role   role;

  public User(Long userId, String username, String password, Role role)
  {
    setUserId(userId);
    setUsername(username);
    setPassword(password);
    setRole(role);
  }

  public Long getUserId()
  {
    return userId;
  }

  public void setUserId(Long userId)
  {
    this.userId = userId;
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

  public Role getRole()
  {
    return role;
  }

  public void setRole(Role role)
  {
    this.role = role;
  }

  public enum Role
  {
    OWNER,
    RENTER
  }

  @Transient
  @Override
  public String getAuthority()
  {
    return "ROLE_" + role.toString();
  }
}

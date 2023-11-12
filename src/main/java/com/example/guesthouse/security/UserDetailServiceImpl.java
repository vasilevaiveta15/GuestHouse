package com.example.guesthouse.security;

import com.example.guesthouse.users.daos.UserDao;
import com.example.guesthouse.users.entity.User;
import com.example.guesthouse.exceptions.InvalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService
{

  private final UserDao userDao;

  @Autowired
  public UserDetailServiceImpl(UserDao userDao)
  {
    this.userDao = userDao;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
  {

    User user = this.userDao.findByUsername(username).orElseThrow(() -> new InvalidException("No such user found!"));

    return org.springframework.security.core.userdetails.User
        .withUsername(username)
        .password(user.getPassword())
        .roles(user.getRole().name())
        .build();
  }
}

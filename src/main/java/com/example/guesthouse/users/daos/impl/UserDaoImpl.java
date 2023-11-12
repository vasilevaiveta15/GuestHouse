package com.example.guesthouse.users.daos.impl;

import com.example.guesthouse.users.daos.UserDao;
import com.example.guesthouse.users.dtos.UserRegistrationRequest;
import com.example.guesthouse.users.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao
{
  private final NamedParameterJdbcOperations namedParameterJdbcOperations;

  @Autowired
  public UserDaoImpl(NamedParameterJdbcOperations namedParameterJdbcOperations){
    this.namedParameterJdbcOperations = namedParameterJdbcOperations;
  }

  @Override
  public void createUser(UserRegistrationRequest userRequest)
  {
      String sql =
          "INSERT INTO USERS           "
         +"               (USERNAME,   "
         +"                PASSWORD,   "
         +"                ROLE)       "
         +"             VALUES         "
         +"               (:username,  "
         +"                :password,  "
         +"                :role)      ";

    SqlParameterSource parameters = new MapSqlParameterSource()
        .addValue("username", userRequest.getUsername())
        .addValue("password", userRequest.getPassword())
        .addValue("role", userRequest.getRole());

    this.namedParameterJdbcOperations.update(sql, parameters);
  }

  @Override
  public Optional<User> findByUsername(String username)
  {
    String sql =
        "SELECT U.USER_ID,              "
       +"       U.USERNAME,             "
       +"       U.PASSWORD,             "
       +"       U.ROLE                  "
       +"   FROM USERS U                "
       +" WHERE U.USERNAME = :username  ";

    SqlParameterSource parameters = new MapSqlParameterSource()
        .addValue("username", username);

    try{
      return this.namedParameterJdbcOperations.queryForObject(sql, parameters,(rs, rowNum) ->
           Optional.of(new User(
              rs.getLong("USER_ID"),
              rs.getString("USERNAME"),
              rs.getString("PASSWORD"),
              rs.getObject("ROLE", User.Role.class)
          )));
    }catch (EmptyResultDataAccessException ex){
      return Optional.empty();
    }
  }
}

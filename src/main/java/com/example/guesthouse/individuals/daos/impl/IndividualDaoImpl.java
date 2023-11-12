package com.example.guesthouse.individuals.daos.impl;

import com.example.guesthouse.individuals.daos.IndividualDao;
import com.example.guesthouse.individuals.dtos.IndividualChangeDto;
import com.example.guesthouse.individuals.dtos.IndividualRequest;
import com.example.guesthouse.individuals.entity.Individual;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class IndividualDaoImpl implements IndividualDao
{
  private final NamedParameterJdbcOperations namedParameterJdbcOperations;

  @Autowired
  public IndividualDaoImpl(NamedParameterJdbcOperations namedParameterJdbcOperations)
  {
    this.namedParameterJdbcOperations = namedParameterJdbcOperations;
  }

  @Override
  public void addIndividual(IndividualRequest individualRequest, boolean isPhoneNumberEmpty, Long userId)
  {
    String sql =
        "INSERT INTO INDIVIDUALS          "
       +"                 (FIRST_NAME,    "
       +"                  MIDDLE_NAME,   "
       +"                  LAST_NAME,     "
       +"                  CARD_NUMBER,   "
       +"                  BIRTH_DATE,    "
       +"                  PHONE_NUMBER,  "
       +"                  EMAIL,         "
       +"                  IS_ACTIVE,     "
       +"                  USER_ID)       "
       +"                VALUES           "
       +"                 (:firstName,    "
       +"                  :middleName,   "
       +"                  :lastName,     "
       +"                  :cardNumber,   "
       +"                  :birthDate,    "
       +"                  :phoneNumber,  "
       +"                  :email,        "
       +"                  :isActive,     "
       +"                  :userId)       ";

    SqlParameterSource parameters = new MapSqlParameterSource()
        .addValue("firstName", individualRequest.getFirstName())
        .addValue("middleName", individualRequest.getMiddleName())
        .addValue("lastName", individualRequest.getLastName())
        .addValue("cardNumber", individualRequest.getCardNumber())
        .addValue("birthDate", individualRequest.getBirthDate())
        .addValue("phoneNumber", individualRequest.getPhoneNumber())
        .addValue("email", individualRequest.getEmail())
        .addValue("isActive", isPhoneNumberEmpty)
        .addValue("userId", userId);

    namedParameterJdbcOperations.update(sql, parameters);
  }

  @Override
  public Optional<Individual> findByUserId(Long userId)
  {
    String sql =
        "SELECT I.INDIVIDUAL_ID,     "
       +"       I.IS_ACTIVE,         "
       +"       I.USER_ID           "
       + "   FROM INDIVIDUALS I      "
       + " WHERE I.USER_ID = :userId ";

    SqlParameterSource parameters = new MapSqlParameterSource()
        .addValue("userId", userId);
    try {
      return namedParameterJdbcOperations.queryForObject(sql, parameters, (rs, rowNum) ->
          Optional.of(new Individual(
              rs.getLong("INDIVIDUAL_ID"),
              rs.getBoolean("IS_ACTIVE"),
              rs.getLong("USER_ID")
              )));
    }
    catch (EmptyResultDataAccessException ex) {
      return Optional.empty();
    }
  }

  @Override
  public void changeIndividual(IndividualChangeDto individualChangeDto, Long userId, boolean isPhoneNumberEmpty)
  {
    String sql =
        "UPDATE INDIVIDUALS                    "
       +"   SET                                "
       +"       PHONE_NUMBER = :phoneNumber,   "
       +"       EMAIL = :email,                "
       +"   IS_ACTIVE = :isActive              "
       +" WHERE USER_ID = :userId              ";

    SqlParameterSource parameters = new MapSqlParameterSource()
        .addValue("phoneNumber", individualChangeDto.getPhoneNumber())
        .addValue("email", individualChangeDto.getEmail())
        .addValue("userId", userId)
        .addValue("isActive", isPhoneNumberEmpty);

    namedParameterJdbcOperations.update(sql, parameters);
  }

  @Override
  public Optional<Individual> findByCardNumber(String cardNumber)
  {
    String sql =
        "SELECT I.CARD_NUMBER               "
       +"  FROM INDIVIDUALS I               "
       +" WHERE I.CARD_NUMBER = :cardNumber ";

    SqlParameterSource parameters = new MapSqlParameterSource()
        .addValue("cardNumber", cardNumber);
    try{
      return namedParameterJdbcOperations.queryForObject(sql, parameters, (rs, rowNum) ->
          Optional.of(new Individual(rs.getString("CARD_NUMBER"))));
    }catch (EmptyResultDataAccessException ex) {
      return Optional.empty();
    }
  }
}

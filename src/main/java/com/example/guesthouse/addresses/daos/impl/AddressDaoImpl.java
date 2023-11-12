package com.example.guesthouse.addresses.daos.impl;

import com.example.guesthouse.addresses.daos.AddressDao;
import com.example.guesthouse.addresses.dtos.AddressAddRequest;
import com.example.guesthouse.addresses.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AddressDaoImpl implements AddressDao
{
  private final NamedParameterJdbcOperations namedParameterJdbcOperations;

  @Autowired
  public AddressDaoImpl(NamedParameterJdbcOperations namedParameterJdbcOperations){
    this.namedParameterJdbcOperations = namedParameterJdbcOperations;
  }

  @Override
  public void addAddress(AddressAddRequest addressRequest, Long userId)
  {
    String sql =
        "INSERT INTO ADDRESSES                        "
       +"               (COUNTRY,                     "
       +"                CITY,                        "
       +"                DISTRICT,                    "
       +"               INDIVIDUAL_ID)                "
       +"               VALUES                        "
       +"               (:country,                    "
       +"                :city,                       "
       +"                :district,                   "
       +"               (SELECT I.INDIVIDUAL_ID       "
       +"                 FROM INDIVIDUALS I          "
       +"                 WHERE I.USER_ID = :userId)) ";

    SqlParameterSource parameters = new MapSqlParameterSource()
        .addValue("country", addressRequest.getCountry())
        .addValue("city", addressRequest.getCity())
        .addValue("district", addressRequest.getDistrict())
        .addValue("userId", userId);

    namedParameterJdbcOperations.update(sql,parameters);
  }

  @Override
  public void changeAddress(AddressAddRequest addressRequest, Long userId)
  {
    String sql =
        "UPDATE ADDRESSES                                  "
       +"SET                                               "
       +"COUNTRY = :country,                               "
       +"CITY = :city,                                     "
       +"DISTRICT = :district                              "
       +"WHERE INDIVIDUAL_ID =                             "
       +"                   (SELECT I.INDIVIDUAL_ID        "
       +"                        FROM INDIVIDUALS I        "
       +"                     WHERE I.USER_ID = :userId)   ";

    SqlParameterSource parameters = new MapSqlParameterSource()
        .addValue("country", addressRequest.getCountry())
        .addValue("city", addressRequest.getCity())
        .addValue("district", addressRequest.getDistrict())
        .addValue("userId", userId);

    namedParameterJdbcOperations.update(sql, parameters);

  }

  @Override
  public Optional<Address>  findByIndividualId(Long individualId)
  {
    String sql =
        "SELECT A.INDIVIDUAL_ID                 "
       +" FROM ADDRESSES A                      "
       +" WHERE A.INDIVIDUAL_ID = :individualId ";

    SqlParameterSource parameters = new MapSqlParameterSource()
        .addValue("individualId", individualId);
    try{
      return namedParameterJdbcOperations.queryForObject(sql, parameters, (rs, rowNum) ->
          Optional.of(new Address(
              rs.getLong("INDIVIDUAL_ID")
          )));

    } catch (EmptyResultDataAccessException ex) {
      return Optional.empty();
    }
  }
}

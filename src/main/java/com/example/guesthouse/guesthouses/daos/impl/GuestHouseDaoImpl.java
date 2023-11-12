package com.example.guesthouse.guesthouses.daos.impl;

import com.example.guesthouse.guesthouses.entity.GuestHouse;
import com.example.guesthouse.guesthouses.dtos.*;
import com.example.guesthouse.guesthouses.daos.GuestHouseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class GuestHouseDaoImpl implements GuestHouseDao
{
  private final NamedParameterJdbcOperations namedParameterJdbcOperations;

  @Autowired
  public GuestHouseDaoImpl(NamedParameterJdbcOperations namedParameterJdbcOperations){
    this.namedParameterJdbcOperations = namedParameterJdbcOperations;
  }

  @Override
  public void addGuestHouse(GuestHouseAddRequest guestHouseRequest, Long ownerId)
  {
    String sql =
        "INSERT INTO GUEST_HOUSES               "
       +"                  (NAME_OF_HOUSE,      "
       +"                   KM_TO_THE_CAPITAL,  "
       +"                   NEAREST_CITY,       "
       +"                   QUADRATURE,         "
       +"                   NUM_OF_FLOORS,      "
       +"                   NUM_OF_RENTED_DAYS, "
       +"                   PAYMENT_PER_DAY,    "
       +"                   OWNER_ID)           "
       +"               VALUES                  "
       +"                  (:nameOfHouse,       "
       +"                   :kmToTheCapital,    "
       +"                   :nearestCity,       "
       +"                   :quadrature,        "
       +"                   :numOfFloors,       "
       +"                   :numOfRenterDays,   "
       +"                   :paymentPerDay,     "
       +"                   :ownerId)           ";

    SqlParameterSource parameters = new MapSqlParameterSource()
        .addValue("nameOfHouse", guestHouseRequest.getNameOfHouse())
        .addValue("kmToTheCapital", guestHouseRequest.getKmToTheCapital())
        .addValue("nearestCity", guestHouseRequest.getNearestCity())
        .addValue("quadrature", guestHouseRequest.getQuadrature())
        .addValue("numOfFloors", guestHouseRequest.getNumOfFloors())
        .addValue("numOfRenterDays", 0)
        .addValue("paymentPerDay", guestHouseRequest.getPaymentPerDay())
        .addValue("ownerId", ownerId);
    namedParameterJdbcOperations.update(sql, parameters);
  }

  @Override
  public void rentHouse(GuestHouseRentDto guestHouseRentDto, Long renterId)
  {
    String sql =
        "UPDATE GUEST_HOUSES                           "
       +"   SET                                        "
       +"       NUM_OF_RENTED_DAYS = :numOfRentedDays, "
       +"       RENTER_ID = :renterId                  "
       +" WHERE GUEST_HOUSE_ID = :guestHouseId         ";

    SqlParameterSource parameters = new MapSqlParameterSource()
        .addValue("numOfRentedDays", guestHouseRentDto.getNumOfRentedDays())
        .addValue("guestHouseId", guestHouseRentDto.getGuestHouseId())
        .addValue("renterId", renterId);

    namedParameterJdbcOperations.update(sql, parameters);
  }

  @Override
  public void changePaymentPerDay(GuestHouseChangePaymentPerDayDto guestHouseChangePaymentPerDayDto)
  {
    String sql =
        "UPDATE GUEST_HOUSES                      "
       +"    SET                                  "
       +"       PAYMENT_PER_DAY = :paymentPerDay  "
       +" WHERE GUEST_HOUSE_ID = :guestHouseId    ";

    SqlParameterSource parameters = new MapSqlParameterSource()
        .addValue("paymentPerDay", guestHouseChangePaymentPerDayDto.getPaymentPerDay())
        .addValue("guestHouseId", guestHouseChangePaymentPerDayDto.getGuestHouseId());

    namedParameterJdbcOperations.update(sql, parameters);
  }

  @Override
  public void releaseHouse(Long guestHouseId, Long ownerId)
  {
    String sql =
        "UPDATE GUEST_HOUSES                          "
       +" SET                                         "
       +"     NUM_OF_RENTED_DAYS = :numOfRentedDays,  "
       +"     RENTER_ID = :renterId                   "
       +"WHERE GUEST_HOUSE_ID = :guestHouseId         "
       +"  AND OWNER_ID = :ownerId                    ";

    SqlParameterSource parameters = new MapSqlParameterSource()
        .addValue("numOfRentedDays", 0)
        .addValue("renterId", null)
        .addValue("guestHouseId", guestHouseId)
        .addValue("ownerId", ownerId);

    namedParameterJdbcOperations.update(sql, parameters);

  }

  @Override
  public void deleteGuestHouse(Long houseNumber, Long ownerId)
  {
    String sql =
        "DELETE FROM GUEST_HOUSES               "
       +" WHERE GUEST_HOUSE_ID = :guestHouseId  "
       +"AND OWNER_ID = :ownerId                ";

    SqlParameterSource parameters = new MapSqlParameterSource()
        .addValue("guestHouseId", houseNumber)
        .addValue("ownerId", ownerId);

    namedParameterJdbcOperations.update(sql, parameters);
  }

  @Override
  public Optional<GuestHouse> findByGuestHouseId(Long houseId)
  {
    String sql =
        "SELECT GH.NUM_OF_RENTED_DAYS,        "
       +"       GH.OWNER_ID                   "
       +"   FROM GUEST_HOUSES GH              "
       +" WHERE GH.GUEST_HOUSE_ID = :houseId ";


    SqlParameterSource parameters = new MapSqlParameterSource()
        .addValue("houseId", houseId);
    try{
      return namedParameterJdbcOperations.queryForObject(sql, parameters,(rs, rowNum) ->
          Optional.of(new GuestHouse(
              rs.getInt("NUM_OF_RENTED_DAYS"),
              rs.getLong("OWNER_ID")
          )));
    } catch (EmptyResultDataAccessException ex) {
      return Optional.empty();
    }
  }

  @Override
  public List<GuestHouseSearchResponse> searchGuestHouse(GuestHouseSearchRequest guestHouseSearchRequest)
  {
    String sql =
        "SELECT GH.GUEST_HOUSE_ID,                                                             "
        +"      GH.NAME_OF_HOUSE,                                                              "
       +"       GH.KM_TO_THE_CAPITAL,                                                          "
       +"       GH.NEAREST_CITY,                                                               "
       +"       GH.QUADRATURE,                                                                 "
       +"       GH.NUM_OF_FLOORS,                                                              "
       +"       GH.PAYMENT_PER_DAY,                                                            "
       +"       I.FIRST_NAME,                                                                  "
       +"       I.MIDDLE_NAME,                                                                 "
       +"       I.LAST_NAME,                                                                   "
       +"       I.PHONE_NUMBER,                                                                "
       +"       I.EMAIL                                                                        "
       +"   FROM GUEST_HOUSES GH                                                               "
       +"   JOIN USERS U                                                                       "
       +"        ON U.USER_ID = GH.OWNER_ID                                                    "
       +"   JOIN INDIVIDUALS I                                                                 "
       +"        ON U.USER_ID = I.USER_ID                                                      "
       +" WHERE                                                                                "
       +"       GH.NUM_OF_RENTED_DAYS = 0                                                      "
       +"   AND GH.NEAREST_CITY LIKE NVL(:nearestCity, '%')                                    "
       +"   AND GH.QUADRATURE >=  NVL(:quadrature, 0)                                          "
       +"   AND GH.NUM_OF_FLOORS >= NVL(:numOfFloors, 0)                                       "
       +"   AND GH.PAYMENT_PER_DAY  BETWEEN NVL(:minPayment, 0) AND NVL(:maxPayment, 10000000) "
       +" ORDER BY GH.PAYMENT_PER_DAY                                                          "
       +"       OFFSET :start                                                                  "
       +"       ROWS FETCH NEXT :end                                                           "
       +"       ROWS ONLY                                                                      ";

    SqlParameterSource parameters = new MapSqlParameterSource()
        .addValue("nearestCity", guestHouseSearchRequest.getNearestCity())
        .addValue("quadrature", guestHouseSearchRequest.getQuadrature())
        .addValue("numOfFloors", guestHouseSearchRequest.getNumOfFloors())
        .addValue("minPayment", guestHouseSearchRequest.getMinPayment())
        .addValue("maxPayment", guestHouseSearchRequest.getMaxPayment())
        .addValue("start", guestHouseSearchRequest.getPageNumber() * guestHouseSearchRequest.getPageCapacity())
        .addValue("end", guestHouseSearchRequest.getPageCapacity());

    return namedParameterJdbcOperations.query(sql, parameters, (rs, rowNum) ->
      new GuestHouseSearchResponse(
          rs.getLong("GUEST_HOUSE_ID"),
          rs.getString("NAME_OF_HOUSE"),
          rs.getBigDecimal("KM_TO_THE_CAPITAL"),
          rs.getString("NEAREST_CITY"),
          rs.getBigDecimal("QUADRATURE"),
          rs.getInt("NUM_OF_FLOORS"),
          rs.getBigDecimal("PAYMENT_PER_DAY"),
          rs.getString("FIRST_NAME"),
          rs.getString("MIDDLE_NAME"),
          rs.getString("LAST_NAME"),
          rs.getString("PHONE_NUMBER"),
          rs.getString("EMAIL")
          ));
  }
}

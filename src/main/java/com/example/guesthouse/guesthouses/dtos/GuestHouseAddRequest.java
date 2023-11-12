package com.example.guesthouse.guesthouses.dtos;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

import static com.example.guesthouse.messages.ExceptionMessages.INVALID_INPUT;
import static com.example.guesthouse.messages.ExceptionMessages.MUST_NOT_BE_EMPTY;

public class GuestHouseAddRequest
{
  @NotEmpty(message = MUST_NOT_BE_EMPTY)
  private String nameOfHouse;

  @DecimalMin(value = "0.0", inclusive = false)
  @Digits(integer = 5, fraction = 2)
  private BigDecimal kmToTheCapital;

  @NotEmpty(message = MUST_NOT_BE_EMPTY)
  private String nearestCity;

  @DecimalMin(value = "0.0", inclusive = false)
  @Digits(integer = 5, fraction = 2)
  private BigDecimal quadrature;

  @Min(value = 0, message = INVALID_INPUT)
  private int numOfFloors;

  @DecimalMin(value = "0.0", inclusive = false)
  @Digits(integer = 5, fraction = 2)
  private BigDecimal paymentPerDay;

  public String getNameOfHouse()
  {
    return nameOfHouse;
  }

  public void setNameOfHouse(String nameOfHouse)
  {
    this.nameOfHouse = nameOfHouse;
  }

  public BigDecimal getKmToTheCapital()
  {
    return kmToTheCapital;
  }

  public void setKmToTheCapital(BigDecimal kmToTheCapital)
  {
    this.kmToTheCapital = kmToTheCapital;
  }

  public String getNearestCity()
  {
    return nearestCity;
  }

  public void setNearestCity(String nearestCity)
  {
    this.nearestCity = nearestCity;
  }

  public BigDecimal getQuadrature()
  {
    return quadrature;
  }

  public void setQuadrature(BigDecimal quadrature)
  {
    this.quadrature = quadrature;
  }

  public int getNumOfFloors()
  {
    return numOfFloors;
  }

  public void setNumOfFloors(int numOfFloors)
  {
    this.numOfFloors = numOfFloors;
  }

  public BigDecimal getPaymentPerDay()
  {
    return paymentPerDay;
  }

  public void setPaymentPerDay(BigDecimal paymentPerDay)
  {
    this.paymentPerDay = paymentPerDay;
  }
}

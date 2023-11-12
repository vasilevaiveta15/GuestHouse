package com.example.guesthouse.guesthouses.dtos;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

import static com.example.guesthouse.messages.ExceptionMessages.INVALID_INPUT;

public class GuestHouseSearchRequest
{
  @Min(value = 0, message = INVALID_INPUT)
  private Integer pageNumber = 0;

  @Min(value = 1, message = INVALID_INPUT)
  @Max(value = 100, message = INVALID_INPUT)
  private Integer pageCapacity = 100;

  private String nearestCity;

  @DecimalMin(value = "0.0", inclusive = false)
  @Digits(integer = 5, fraction = 2)
  private BigDecimal quadrature;

  @Min(value = 0, message = INVALID_INPUT)
  private int numOfFloors;

  @DecimalMin(value = "0.0", inclusive = false)
  @Digits(integer = 5, fraction = 2)
  private BigDecimal minPayment;

  @DecimalMin(value = "0.0", inclusive = false)
  @Digits(integer = 5, fraction = 2)
  private BigDecimal maxPayment;

  public Integer getPageNumber()
  {
    return pageNumber;
  }

  public void setPageNumber(Integer pageNumber)
  {
    this.pageNumber = pageNumber;
  }

  public Integer getPageCapacity()
  {
    return pageCapacity;
  }

  public void setPageCapacity(Integer pageCapacity)
  {
    this.pageCapacity = pageCapacity;
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

  public BigDecimal getMinPayment()
  {
    return minPayment;
  }

  public void setMinPayment(BigDecimal minPayment)
  {
    this.minPayment = minPayment;
  }

  public BigDecimal getMaxPayment()
  {
    return maxPayment;
  }

  public void setMaxPayment(BigDecimal maxPayment)
  {
    this.maxPayment = maxPayment;
  }
}

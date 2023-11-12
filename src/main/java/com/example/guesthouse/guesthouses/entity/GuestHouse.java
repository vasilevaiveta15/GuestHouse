package com.example.guesthouse.guesthouses.entity;

import java.math.BigDecimal;

public class GuestHouse
{
  private Long       houseId;
  private String     nameOfHouse;
  private BigDecimal kmToTheCapital;
  private String     nearestCity;
  private BigDecimal quadrature;
  private int        numOfFloors;
  private int        numOfRentedDays;
  private BigDecimal paymentPerDay;
  private Long       ownerId;
  private Long       renterId;

  public GuestHouse(int numOfRentedDays, Long ownerId)
  {
    setNumOfRentedDays(numOfRentedDays);
    setOwnerId(ownerId);
  }

  public Long getHouseId()
  {
    return houseId;
  }

  public void setHouseId(Long houseId)
  {
    this.houseId = houseId;
  }

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

  public int getNumOfRentedDays()
  {
    return numOfRentedDays;
  }

  public void setNumOfRentedDays(int numOfRentedDays)
  {
    this.numOfRentedDays = numOfRentedDays;
  }

  public BigDecimal getPaymentPerDay()
  {
    return paymentPerDay;
  }

  public void setPaymentPerDay(BigDecimal paymentPerDay)
  {
    this.paymentPerDay = paymentPerDay;
  }

  public Long getOwnerId()
  {
    return ownerId;
  }

  public void setOwnerId(Long ownerId)
  {
    this.ownerId = ownerId;
  }

  public Long getRenterId()
  {
    return renterId;
  }

  public void setRenterId(Long renterId)
  {
    this.renterId = renterId;
  }
}

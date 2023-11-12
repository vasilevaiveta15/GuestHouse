package com.example.guesthouse.guesthouses.dtos;

import java.math.BigDecimal;

public class GuestHouseAddResponse
{
  private String     nameOfHouse;
  private BigDecimal kmToTheCapital;
  private String     nearestCity;
  private BigDecimal quadrature;
  private int        numOfFloors;

  public GuestHouseAddResponse(String nameOfHouse, BigDecimal kmToTheCapital, String nearestCity, BigDecimal quadrature, int numOfFloors)
  {
    setNameOfHouse(nameOfHouse);
    setKmToTheCapital(kmToTheCapital);
    setNearestCity(nearestCity);
    setQuadrature(quadrature);
    setNumOfFloors(numOfFloors);
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
}

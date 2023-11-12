package com.example.guesthouse.guesthouses.dtos;

import java.math.BigDecimal;

public class GuestHouseSearchResponse
{
  private Long       houseNumber;
  private String     nameOfHouse;
  private BigDecimal kmToTheCapital;
  private String     nearestCity;
  private BigDecimal quadrature;
  private Integer    numOfFloors;
  private BigDecimal paymentPerDay;
  private String     firstName;
  private String     middleName;
  private String     lastName;
  private String     phoneNumber;
  private String     email;

  public GuestHouseSearchResponse(Long houseNumber, String nameOfHouse, BigDecimal kmToTheCapital, String nearestCity,
                                  BigDecimal quadrature, Integer numOfFloors, BigDecimal paymentPerDay,
                                  String firstName, String middleName, String lastName, String phoneNumber, String email)
  {
    setHouseNumber(houseNumber);
    setNameOfHouse(nameOfHouse);
    setKmToTheCapital(kmToTheCapital);
    setNearestCity(nearestCity);
    setQuadrature(quadrature);
    setNumOfFloors(numOfFloors);
    setPaymentPerDay(paymentPerDay);
    setFirstName(firstName);
    setMiddleName(middleName);
    setLastName(lastName);
    setPhoneNumber(phoneNumber);
    setEmail(email);
  }

  public Long getHouseNumber()
  {
    return houseNumber;
  }

  public void setHouseNumber(Long houseNumber)
  {
    this.houseNumber = houseNumber;
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

  public Integer getNumOfFloors()
  {
    return numOfFloors;
  }

  public void setNumOfFloors(Integer numOfFloors)
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

  public String getFirstName()
  {
    return firstName;
  }

  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }

  public String getMiddleName()
  {
    return middleName;
  }

  public void setMiddleName(String middleName)
  {
    this.middleName = middleName;
  }

  public String getLastName()
  {
    return lastName;
  }

  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }

  public String getPhoneNumber()
  {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber)
  {
    this.phoneNumber = phoneNumber;
  }

  public String getEmail()
  {
    return email;
  }

  public void setEmail(String email)
  {
    this.email = email;
  }
}

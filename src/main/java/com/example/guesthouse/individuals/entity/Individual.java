package com.example.guesthouse.individuals.entity;

import java.util.Date;

public class Individual
{
  private Long    individualId;
  private String  firstName;
  private String  middleName;
  private String  lastName;
  private String  cardNumber;
  private Date    birthDate;
  private String  phoneNumber;
  private String  email;
  private boolean isActive;
  private Long    userId;

  public Individual(Long individualId, boolean isActive, Long userId)
  {
    setIndividualId(individualId);
    setActive(isActive);
    setUserId(userId);
  }

  public Individual(String cardNumber)
  {
    this.cardNumber = cardNumber;
  }

  public Long getIndividualId()
  {
    return individualId;
  }

  public void setIndividualId(Long individualId)
  {
    this.individualId = individualId;
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

  public String getCardNumber()
  {
    return cardNumber;
  }

  public void setCardNumber(String cardNumber)
  {
    this.cardNumber = cardNumber;
  }

  public Date getBirthDate()
  {
    return birthDate;
  }

  public void setBirthDate(Date birthDate)
  {
    this.birthDate = birthDate;
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

  public boolean isActive()
  {
    return isActive;
  }

  public void setActive(boolean active)
  {
    isActive = active;
  }

  public Long getUserId()
  {
    return userId;
  }

  public void setUserId(Long userId)
  {
    this.userId = userId;
  }
}

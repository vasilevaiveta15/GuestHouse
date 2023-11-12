package com.example.guesthouse.individuals.dtos;

import java.util.Date;

public class IndividualAddResponse
{
  private String firstName;
  private String middleName;
  private String lastName;
  private String cardNumber;
  private Date   birthDate;
  private String phoneNumber;
  private String email;

  public IndividualAddResponse(String firstName, String middleName, String lastName,
                               String cardNumber, Date birthDate, String phoneNumber, String email)
  {
    setFirstName(firstName);
    setMiddleName(middleName);
    setLastName(lastName);
    setCardNumber(cardNumber);
    setBirthDate(birthDate);
    setPhoneNumber(phoneNumber);
    setEmail(email);
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
}

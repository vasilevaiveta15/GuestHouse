package com.example.guesthouse.individuals.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Date;

import static com.example.guesthouse.messages.ExceptionMessages.*;

public class IndividualRequest
{
  @NotEmpty(message = MUST_NOT_BE_EMPTY)
  private String firstName;

  @NotEmpty(message = MUST_NOT_BE_EMPTY)
  private String middleName;

  @NotEmpty(message = MUST_NOT_BE_EMPTY)
  private String lastName;

  @NotEmpty(message = MUST_NOT_BE_EMPTY)
  @Pattern(regexp = "^4[0-9]{12}(?:[0-9]{3})?$", message = INVALID_INPUT)
  private String cardNumber;

  private Date birthDate;

  private String phoneNumber;

  @NotEmpty(message = MUST_NOT_BE_EMPTY)
  @Pattern(regexp = "([^-_][a-zA-Z0-9\\._]+[^-_])@([a-zA-Z0-9]*\\.[a-zA-Z0-9]+)+", message = INVALID_EMAIL)
  private String email;

  public IndividualRequest()
  {
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

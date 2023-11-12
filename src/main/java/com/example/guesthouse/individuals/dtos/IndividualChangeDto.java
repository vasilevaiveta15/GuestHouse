package com.example.guesthouse.individuals.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import static com.example.guesthouse.messages.ExceptionMessages.INVALID_EMAIL;
import static com.example.guesthouse.messages.ExceptionMessages.MUST_NOT_BE_EMPTY;

public class IndividualChangeDto
{
  @NotEmpty(message = MUST_NOT_BE_EMPTY)
  private String phoneNumber;

  @NotEmpty(message = MUST_NOT_BE_EMPTY)
  @Pattern(regexp = "([^-_][a-zA-Z0-9\\._]+[^-_])@([a-zA-Z0-9]*\\.[a-zA-Z0-9]+)+", message = INVALID_EMAIL)
  private String email;

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

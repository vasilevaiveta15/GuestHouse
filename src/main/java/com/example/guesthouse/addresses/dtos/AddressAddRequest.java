package com.example.guesthouse.addresses.dtos;

import javax.validation.constraints.NotEmpty;

import static com.example.guesthouse.messages.ExceptionMessages.MUST_NOT_BE_EMPTY;

public class AddressAddRequest
{
  @NotEmpty(message = MUST_NOT_BE_EMPTY)
  private String country;

  @NotEmpty(message = MUST_NOT_BE_EMPTY)
  private String city;

  @NotEmpty(message = MUST_NOT_BE_EMPTY)
  private String district;

  public AddressAddRequest()
  {
  }

  public String getCountry()
  {
    return country;
  }

  public void setCountry(String country)
  {
    this.country = country;
  }

  public String getCity()
  {
    return city;
  }

  public void setCity(String city)
  {
    this.city = city;
  }

  public String getDistrict()
  {
    return district;
  }

  public void setDistrict(String district)
  {
    this.district = district;
  }
}

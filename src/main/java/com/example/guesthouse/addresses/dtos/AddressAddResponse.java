package com.example.guesthouse.addresses.dtos;

public class AddressAddResponse
{
  private String country;
  private String city;
  private String district;

  public AddressAddResponse(String country, String city, String district)
  {
    setCountry(country);
    setCity(city);
    setDistrict(district);
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

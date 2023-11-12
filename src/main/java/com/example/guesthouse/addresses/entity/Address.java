package com.example.guesthouse.addresses.entity;

public class Address
{
  private Long   addressId;
  private String country;
  private String city;
  private String district;
  private Long   individualId;

  public Address(Long individualId)
  {
    setIndividualId(individualId);
  }

  public Long getAddressId()
  {
    return addressId;
  }

  public void setAddressId(Long addressId)
  {
    this.addressId = addressId;
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

  public Long getIndividualId()
  {
    return individualId;
  }

  public void setIndividualId(Long individualId)
  {
    this.individualId = individualId;
  }
}

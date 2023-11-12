package com.example.guesthouse.guesthouses.services;

import com.example.guesthouse.guesthouses.dtos.*;

import java.util.List;

public interface GuestHouseService
{
  GuestHouseAddResponse addGuestHouse(GuestHouseAddRequest guestHouseRequest);

  String rentHouse(GuestHouseRentDto guestHouseRentDto);

  String changePaymentPerDay(GuestHouseChangePaymentPerDayDto guestHouseChangePaymentPerDayDto);

  String releaseHouse(Long houseNumber);

  String deleteGuestHouse(Long houseNumber);

  List<GuestHouseSearchResponse> searchHouse(GuestHouseSearchRequest guestHouseSearchRequest);

}

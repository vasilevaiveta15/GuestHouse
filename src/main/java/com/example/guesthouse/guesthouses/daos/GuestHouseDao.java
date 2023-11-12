package com.example.guesthouse.guesthouses.daos;

import com.example.guesthouse.guesthouses.entity.GuestHouse;
import com.example.guesthouse.guesthouses.dtos.*;

import java.util.List;
import java.util.Optional;

public interface GuestHouseDao
{
  void addGuestHouse(GuestHouseAddRequest guestHouseRequest, Long userId);

  void rentHouse(GuestHouseRentDto guestHouseRentDto, Long renterId);

  void changePaymentPerDay(GuestHouseChangePaymentPerDayDto guestHouseChangePaymentPerDayDto);

  void releaseHouse(Long guestHouseId, Long ownerId);

  void deleteGuestHouse(Long houseNumber, Long ownerId);

  Optional<GuestHouse> findByGuestHouseId(Long houseId);

  List<GuestHouseSearchResponse> searchGuestHouse(GuestHouseSearchRequest guestHouseSearchRequest);
}

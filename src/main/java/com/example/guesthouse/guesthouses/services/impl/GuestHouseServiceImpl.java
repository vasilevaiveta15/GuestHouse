package com.example.guesthouse.guesthouses.services.impl;

import com.example.guesthouse.exceptions.InvalidException;
import com.example.guesthouse.guesthouses.daos.GuestHouseDao;
import com.example.guesthouse.guesthouses.dtos.*;
import com.example.guesthouse.guesthouses.entity.GuestHouse;
import com.example.guesthouse.guesthouses.services.GuestHouseService;
import com.example.guesthouse.individuals.daos.IndividualDao;
import com.example.guesthouse.individuals.entity.Individual;
import com.example.guesthouse.users.entity.User;
import com.example.guesthouse.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.guesthouse.messages.ExceptionMessages.*;
import static com.example.guesthouse.messages.OutputMessages.*;

@Service
@Transactional
public class GuestHouseServiceImpl implements GuestHouseService
{
  private final GuestHouseDao guestHouseDao;
  private final UserService   userService;
  private final IndividualDao individualDao;

  @Autowired
  public GuestHouseServiceImpl(GuestHouseDao guestHouseDao, UserService userService, IndividualDao individualDao)
  {
    this.guestHouseDao = guestHouseDao;
    this.userService = userService;
    this.individualDao = individualDao;
  }

  @Override
  public GuestHouseAddResponse addGuestHouse(GuestHouseAddRequest guestHouseRequest)
  {
    User currentLoggedUser = assertionForIndividual();
    guestHouseDao.addGuestHouse(guestHouseRequest, currentLoggedUser.getUserId());
    return new GuestHouseAddResponse(guestHouseRequest.getNameOfHouse(), guestHouseRequest.getKmToTheCapital(),
        guestHouseRequest.getNearestCity(), guestHouseRequest.getQuadrature(), guestHouseRequest.getNumOfFloors());
  }

  @Override
  public String rentHouse(GuestHouseRentDto guestHouseRentDto)
  {
    User currentLoggedUser = assertionForIndividual();
    GuestHouse guestHouse = guestHouseDao.findByGuestHouseId(guestHouseRentDto.getGuestHouseId())
        .orElseThrow(() -> new InvalidException(INVALID_HOUSE_NUMBER));

    if (guestHouse.getNumOfRentedDays() > 0) {
      throw new InvalidException(HOUSE_ALREADY_RENTED);
    }
    guestHouseDao.rentHouse(guestHouseRentDto, currentLoggedUser.getUserId());
    return SUCCESSFULLY_RENT_GUEST_HOUSE;
  }

  @Override
  public String changePaymentPerDay(GuestHouseChangePaymentPerDayDto guestHouseChangePaymentPerDayDto)
  {
    User currentLoggedUser = assertionForIndividual();

    GuestHouse guestHouse = guestHouseDao.findByGuestHouseId(guestHouseChangePaymentPerDayDto.getGuestHouseId())
        .orElseThrow(() -> new InvalidException(INVALID_HOUSE_NUMBER));

    if (!guestHouse.getOwnerId().equals(currentLoggedUser.getUserId())) {
      throw new InvalidException(HOUSE_IS_NOT_YOURS);
    }

    guestHouseDao.changePaymentPerDay(guestHouseChangePaymentPerDayDto);
    return SUCCESSFULLY_CHANGE_PAYMENT_PER_DAY;
  }

  @Override
  public String releaseHouse(Long houseNumber)
  {
    User currentLoggedUser = assertionForIndividual();

    GuestHouse guestHouse = guestHouseDao.findByGuestHouseId(houseNumber)
        .orElseThrow(() -> new InvalidException(INVALID_HOUSE_NUMBER));

    if (!guestHouse.getOwnerId().equals(currentLoggedUser.getUserId())) {
      throw new InvalidException(HOUSE_IS_NOT_YOURS);
    }

    if (guestHouse.getNumOfRentedDays() == 0) {
      throw new InvalidException(HOUSE_IS_NOT_RENTED_YET);
    }

    guestHouseDao.releaseHouse(houseNumber, currentLoggedUser.getUserId());
    return SUCCESSFULLY_RELEASE_HOUSE;
  }

  @Override
  public String deleteGuestHouse(Long houseNumber)
  {
    User currentLoggedUser = assertionForIndividual();

    GuestHouse guestHouse = guestHouseDao.findByGuestHouseId(houseNumber)
        .orElseThrow(() -> new InvalidException(INVALID_HOUSE_NUMBER));

    if (!guestHouse.getOwnerId().equals(currentLoggedUser.getUserId())) {
      throw new InvalidException(HOUSE_IS_NOT_YOURS);
    }
    guestHouseDao.deleteGuestHouse(houseNumber, currentLoggedUser.getUserId());
    return SUCCESSFULLY_REMOVE_HOUSE;
  }

  @Override
  public List<GuestHouseSearchResponse> searchHouse(GuestHouseSearchRequest guestHouseSearchRequest)
  {
    return guestHouseDao.searchGuestHouse(guestHouseSearchRequest);
  }

  private User assertionForIndividual()
  {
    User currentLoggedUser = userService.getCurrentLoggedUser();
    Individual individual = individualDao.findByUserId(currentLoggedUser
        .getUserId()).orElseThrow(() -> new InvalidException(THERE_IS_NO_INDIVIDUAL));

    if (individual.isActive()) {
      throw new InvalidException(INDIVIDUAL_NOT_ACTIVE);
    }
    return currentLoggedUser;
  }
}

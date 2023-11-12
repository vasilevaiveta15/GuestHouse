package com.example.guesthouse.addresses.services.impl;

import com.example.guesthouse.addresses.daos.AddressDao;
import com.example.guesthouse.addresses.services.AddressService;
import com.example.guesthouse.individuals.daos.IndividualDao;
import com.example.guesthouse.addresses.dtos.AddressAddRequest;
import com.example.guesthouse.addresses.dtos.AddressAddResponse;
import com.example.guesthouse.individuals.entity.Individual;
import com.example.guesthouse.users.services.UserService;
import com.example.guesthouse.users.entity.User;
import com.example.guesthouse.exceptions.InvalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.guesthouse.messages.ExceptionMessages.*;
import static com.example.guesthouse.messages.OutputMessages.SUCCESSFULLY_CHANGE_ADDRESS;

@Service
@Transactional
public class AddressServiceImpl implements AddressService
{
  private final AddressDao    addressDao;
  private final UserService   userService;
  private final IndividualDao individualDao;

  @Autowired
  public AddressServiceImpl(AddressDao addressDao, UserService userService, IndividualDao individualDao)
  {
    this.addressDao = addressDao;
    this.userService = userService;
    this.individualDao = individualDao;
  }

  @Override
  public AddressAddResponse addAddress(AddressAddRequest addressRequest)
  {
    User currentLoggedUser = userService.getCurrentLoggedUser();
    Individual individual = individualDao.findByUserId(currentLoggedUser
        .getUserId()).orElseThrow(() -> new InvalidException(THERE_IS_NO_INDIVIDUAL));

    if (individual.isActive()) {
      throw new InvalidException(INDIVIDUAL_NOT_ACTIVE);
    }

    addressDao.addAddress(addressRequest, currentLoggedUser.getUserId());
    return new AddressAddResponse(addressRequest.getCountry(), addressRequest.getCity(),
        addressRequest.getDistrict());

  }

  @Override
  public String changeAddress(AddressAddRequest addressRequest)
  {
    User currentLoggedUser = userService.getCurrentLoggedUser();
    Individual individual = individualDao.findByUserId(currentLoggedUser.getUserId())
        .orElseThrow(() -> new InvalidException(THERE_IS_NO_INDIVIDUAL));

    if (individual.isActive()) {
      throw new InvalidException(INDIVIDUAL_NOT_ACTIVE);
    }
    if (addressDao.findByIndividualId(individual.getIndividualId()).isEmpty()) {
      throw new InvalidException(ADDRESS_NOT_EXIST);
    }

    addressDao.changeAddress(addressRequest, currentLoggedUser.getUserId());
    return SUCCESSFULLY_CHANGE_ADDRESS;
  }
}

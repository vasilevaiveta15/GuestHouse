package com.example.guesthouse.individuals.services.impl;

import com.example.guesthouse.exceptions.InvalidException;
import com.example.guesthouse.individuals.daos.IndividualDao;
import com.example.guesthouse.individuals.dtos.IndividualAddResponse;
import com.example.guesthouse.individuals.dtos.IndividualChangeDto;
import com.example.guesthouse.individuals.dtos.IndividualRequest;
import com.example.guesthouse.individuals.entity.Individual;
import com.example.guesthouse.individuals.services.IndividualService;
import com.example.guesthouse.users.entity.User;
import com.example.guesthouse.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.guesthouse.messages.ExceptionMessages.*;
import static com.example.guesthouse.messages.OutputMessages.SUCCESSFULLY_CHANGE_INDIVIDUAL;

@Service
@Transactional
public class IndividualServiceImpl implements IndividualService
{
  private final IndividualDao individualDao;
  private final UserService   userService;

  @Autowired
  public IndividualServiceImpl(IndividualDao individualDao, UserService userService)
  {
    this.individualDao = individualDao;
    this.userService = userService;
  }

  @Override
  public IndividualAddResponse addIndividual(IndividualRequest individualRequest)
  {
    User currentLoggedUser = userService.getCurrentLoggedUser();

    if (individualDao.findByUserId(currentLoggedUser.getUserId()).isPresent()) {
      throw new InvalidException(INDIVIDUAL_ALREADY_EXIST);
    }

    if (individualDao.findByCardNumber(individualRequest.getCardNumber()).isPresent()) {
      throw new InvalidException(CARD_NUMBER_ALREADY_EXIST);
    }

    boolean isPhoneNumberEmpty = true;

    if (null != individualRequest.getPhoneNumber() && !individualRequest.getPhoneNumber().isEmpty()) {
      boolean isCorrectPhoneNumber = validationForPhoneNumber(individualRequest.getPhoneNumber());
      if (isCorrectPhoneNumber) {
        throw new InvalidException(INVALID_PHONE_NUMBER);
      }
      isPhoneNumberEmpty = false;
    }
    individualDao.addIndividual(individualRequest, isPhoneNumberEmpty, currentLoggedUser.getUserId());

    return new IndividualAddResponse(individualRequest.getFirstName(), individualRequest.getMiddleName(),
        individualRequest.getLastName(), individualRequest.getCardNumber(), individualRequest.getBirthDate(),
        individualRequest.getPhoneNumber(), individualRequest.getEmail());
  }

  @Override
  public String changeIndividual(IndividualChangeDto individualChangeDto)
  {
    User currentLoggedUser = userService.getCurrentLoggedUser();
    Individual individual = individualDao.findByUserId(currentLoggedUser
        .getUserId()).orElseThrow(() -> new InvalidException(THERE_IS_NO_INDIVIDUAL));

    if (individual.isActive()) {
      throw new InvalidException(INDIVIDUAL_NOT_ACTIVE);
    }
    boolean isPhoneNumberEmpty = true;

    if (!individualChangeDto.getPhoneNumber().isEmpty()) {
      boolean isCorrectPhoneNumber = validationForPhoneNumber(individualChangeDto.getPhoneNumber());
      if (isCorrectPhoneNumber) {
        throw new InvalidException(INVALID_PHONE_NUMBER);
      }
      isPhoneNumberEmpty = false;
    }
    individualDao.changeIndividual(individualChangeDto, currentLoggedUser.getUserId(), isPhoneNumberEmpty);
    return SUCCESSFULLY_CHANGE_INDIVIDUAL;
  }

  private boolean validationForPhoneNumber(String number)
  {
    if (number.length() == 13 || number.length() == 10) {
      number.split("(\\+)?(359|0)8[789]\\d{1}(|-| )\\d{3}(|-| )\\d{3}");
      return false;
    }
    else if (number.length() == 14) {
      number.split("(00)?(359)8[789]\\d{1}(|-| )\\d{3}(|-| )\\d{3}");
      return false;
    }
    return true;
  }
}

package com.example.guesthouse.individuals.daos;

import com.example.guesthouse.individuals.dtos.IndividualChangeDto;
import com.example.guesthouse.individuals.dtos.IndividualRequest;
import com.example.guesthouse.individuals.entity.Individual;

import java.util.Optional;

public interface IndividualDao
{
  void addIndividual(IndividualRequest individualRequest,
                     boolean isPhoneNumberEmpty, Long userId);

  Optional<Individual> findByUserId(Long userId);

  void changeIndividual(IndividualChangeDto individualChangeDto,
                        Long userId, boolean isPhoneNumberEmpty);

  Optional<Individual> findByCardNumber(String cardNumber);

}

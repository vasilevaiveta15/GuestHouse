package com.example.guesthouse.individuals.services;

import com.example.guesthouse.individuals.dtos.IndividualAddResponse;
import com.example.guesthouse.individuals.dtos.IndividualChangeDto;
import com.example.guesthouse.individuals.dtos.IndividualRequest;

public interface IndividualService
{
  IndividualAddResponse addIndividual(IndividualRequest individualRequest);

  String changeIndividual(IndividualChangeDto individualChangeDto);
}

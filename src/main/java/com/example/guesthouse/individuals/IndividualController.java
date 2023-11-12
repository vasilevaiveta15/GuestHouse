package com.example.guesthouse.individuals;

import com.example.guesthouse.individuals.dtos.IndividualChangeDto;
import com.example.guesthouse.individuals.dtos.IndividualRequest;
import com.example.guesthouse.individuals.services.IndividualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping("/api/v1")
public class IndividualController
{
  private final IndividualService individualService;

  @Autowired
  public IndividualController(IndividualService individualService)
  {
    this.individualService = individualService;
  }

  @PreAuthorize("hasAnyRole('OWNER, RENTER')")
  @PostMapping("/add/id/card")
  public ResponseEntity<?> addIndividual(@RequestBody @Valid IndividualRequest individualRequest)
  {
    return ResponseEntity.ok(individualService.addIndividual(individualRequest));
  }

  @PreAuthorize("hasAnyRole('OWNER, RENTER')")
  @PatchMapping("/change/individual")
  public ResponseEntity<?> changeIndividual(@RequestBody @Valid IndividualChangeDto individualChangeDto)
  {
    return ResponseEntity.ok(individualService.changeIndividual(individualChangeDto));
  }
}

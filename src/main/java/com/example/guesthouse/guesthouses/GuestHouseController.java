package com.example.guesthouse.guesthouses;

import com.example.guesthouse.guesthouses.dtos.GuestHouseAddRequest;
import com.example.guesthouse.guesthouses.dtos.GuestHouseChangePaymentPerDayDto;
import com.example.guesthouse.guesthouses.dtos.GuestHouseRentDto;
import com.example.guesthouse.guesthouses.dtos.GuestHouseSearchRequest;
import com.example.guesthouse.guesthouses.services.GuestHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import static com.example.guesthouse.messages.ExceptionMessages.INVALID_NUMBER;

@RestController
@Validated
@RequestMapping("/api/v1")
public class GuestHouseController
{
  private final GuestHouseService guestHouseService;

  @Autowired
  public GuestHouseController(GuestHouseService guestHouseService)
  {
    this.guestHouseService = guestHouseService;
  }

  @PreAuthorize("hasRole('OWNER')")
  @PostMapping("/add/guest/house")
  public ResponseEntity<?> addGuestHouse(@RequestBody @Valid GuestHouseAddRequest guestHouseRequest)
  {
    return ResponseEntity.ok(guestHouseService.addGuestHouse(guestHouseRequest));
  }

  @PreAuthorize("hasRole('RENTER')")
  @PatchMapping("/rent/house")
  public ResponseEntity<?> rentHouse(@RequestBody @Valid GuestHouseRentDto guestHouseRentDto)
  {
    return ResponseEntity.ok(guestHouseService.rentHouse(guestHouseRentDto));
  }

  @PreAuthorize("hasRole('OWNER')")
  @PatchMapping("/change/payment/per/day")
  public ResponseEntity<?> changePaymentPerDay(@RequestBody @Valid GuestHouseChangePaymentPerDayDto guestHouseChangePaymentPerDayDto)
  {
    return ResponseEntity.ok(guestHouseService.changePaymentPerDay(guestHouseChangePaymentPerDayDto));
  }

  @PreAuthorize("hasRole('OWNER')")
  @PatchMapping("/release/house/{houseNumber}")
  public ResponseEntity<?> releaseHouse(@PathVariable @Min(value = 0, message = INVALID_NUMBER) Long houseNumber)
  {
    return ResponseEntity.ok(guestHouseService.releaseHouse(houseNumber));
  }

  @PreAuthorize("hasRole('OWNER')")
  @DeleteMapping("/remove/house/{houseNumber}")
  public ResponseEntity<?> removeHouse(@PathVariable @Min(value = 0, message = INVALID_NUMBER) Long houseNumber)
  {
    return ResponseEntity.ok(guestHouseService.deleteGuestHouse(houseNumber));
  }

  //bind @RequestParam to object - request parameters inside POJO
  @PreAuthorize("hasAnyRole('OWNER, RENTER')")
  @GetMapping("/search/by/criteria")
  public ResponseEntity<?> searchHouse(@Valid GuestHouseSearchRequest guestHouseSearchRequest)
  {
    return ResponseEntity.ok(guestHouseService.searchHouse(guestHouseSearchRequest));
  }
}

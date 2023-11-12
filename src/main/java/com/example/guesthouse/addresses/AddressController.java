package com.example.guesthouse.addresses;

import com.example.guesthouse.addresses.dtos.AddressAddRequest;
import com.example.guesthouse.addresses.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class AddressController
{
  private final AddressService addressService;

  @Autowired
  public AddressController(AddressService addressService)
  {
    this.addressService = addressService;
  }

  @PreAuthorize("hasAnyRole('OWNER, RENTER')")
  @PostMapping("/add/address")
  public ResponseEntity<?> addAddress(@RequestBody @Valid AddressAddRequest addressRequest)
  {
    return ResponseEntity.ok(addressService.addAddress(addressRequest));
  }

  @PreAuthorize("hasAnyRole('OWNER, RENTER')")
  @PatchMapping("/change/address")
  public ResponseEntity<?> changeAddress(@RequestBody @Valid AddressAddRequest addressRequest)
  {
    return ResponseEntity.ok(addressService.changeAddress(addressRequest));
  }
}

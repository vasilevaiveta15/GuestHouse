package com.example.guesthouse.addresses.services;

import com.example.guesthouse.addresses.dtos.AddressAddRequest;
import com.example.guesthouse.addresses.dtos.AddressAddResponse;

public interface AddressService
{
  AddressAddResponse addAddress(AddressAddRequest addressRequest);

  String changeAddress(AddressAddRequest addressRequest);
}

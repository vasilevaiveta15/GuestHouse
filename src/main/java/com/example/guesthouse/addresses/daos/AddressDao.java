package com.example.guesthouse.addresses.daos;

import com.example.guesthouse.addresses.dtos.AddressAddRequest;
import com.example.guesthouse.addresses.entity.Address;

import java.util.Optional;

public interface AddressDao
{
  void addAddress(AddressAddRequest addressRequest, Long userId);

  void changeAddress(AddressAddRequest addressRequest, Long userId);

  Optional<Address> findByIndividualId(Long individualId);

}

package com.example.guesthouse.guesthouses.dtos;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import static com.example.guesthouse.messages.ExceptionMessages.*;

public class GuestHouseRentDto
{
  @Min(value = 0, message = INVALID_NUMBER)
  @NotNull(message = MUST_NOT_BE_EMPTY)
  private Long guestHouseId;

  @Min(value = 1, message = INVALID_INPUT)
  @NotNull(message = MUST_NOT_BE_EMPTY)
  private int numOfRentedDays;

  public GuestHouseRentDto()
  {
  }

  public Long getGuestHouseId()
  {
    return guestHouseId;
  }

  public void setGuestHouseId(Long guestHouseId)
  {
    this.guestHouseId = guestHouseId;
  }

  public int getNumOfRentedDays()
  {
    return numOfRentedDays;
  }

  public void setNumOfRentedDays(int numOfRentedDays)
  {
    this.numOfRentedDays = numOfRentedDays;
  }

}

package com.personalprojects.ticketblitz.DTO.Response;

import com.personalprojects.ticketblitz.Entity.Seat;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SeatAvailabilityResponseDTO {
  private List<Seat> availableSeats;
  private List<Seat> bookedSeats;
}

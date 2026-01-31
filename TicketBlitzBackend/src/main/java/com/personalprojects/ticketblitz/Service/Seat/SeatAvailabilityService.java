package com.personalprojects.ticketblitz.Service.Seat;

import com.personalprojects.ticketblitz.DTO.Response.SeatAvailabilityResponseDTO;
import java.util.UUID;

public interface SeatAvailabilityService {
  SeatAvailabilityResponseDTO seatAvailability(UUID showId);
}

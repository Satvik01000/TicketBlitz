package com.personalprojects.ticketblitz.DTO.Request;

import java.util.UUID;
import lombok.Getter;

@Getter
public class BookingCreationRequestDTO {
  private UUID showId;
  private UUID seatId;
  private UUID userId;
}

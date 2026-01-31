package com.personalprojects.ticketblitz.Controller;

import com.personalprojects.ticketblitz.DTO.Response.SeatAvailabilityResponseDTO;
import com.personalprojects.ticketblitz.Service.Seat.SeatAvailabilityService;
import java.util.UUID;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/show")
public class SeatAvailabilityController {

  private final SeatAvailabilityService seatAvailabilityService;

  public SeatAvailabilityController(SeatAvailabilityService seatAvailabilityService) {
    this.seatAvailabilityService = seatAvailabilityService;
  }

  @GetMapping("/{showId}/seat")
  public SeatAvailabilityResponseDTO getSeatAvailability(@PathVariable UUID showId) {
    return seatAvailabilityService.seatAvailability(showId);
  }
}

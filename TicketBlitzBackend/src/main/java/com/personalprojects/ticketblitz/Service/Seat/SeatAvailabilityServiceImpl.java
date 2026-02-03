package com.personalprojects.ticketblitz.Service.Seat;

import com.personalprojects.ticketblitz.DTO.Response.SeatAvailabilityResponseDTO;
import com.personalprojects.ticketblitz.Entity.*;
import com.personalprojects.ticketblitz.Exceptions.NotFoundException;
import com.personalprojects.ticketblitz.Repository.BookingRepo;
import com.personalprojects.ticketblitz.Repository.SeatRepo;
import com.personalprojects.ticketblitz.Repository.ShowRepo;
import java.util.*;
import org.springframework.stereotype.Service;

@Service
public class SeatAvailabilityServiceImpl implements SeatAvailabilityService {
  private final SeatRepo seatRepo;
  private final BookingRepo bookingRepo;
  private final ShowRepo showRepo;

  public SeatAvailabilityServiceImpl(
      SeatRepo seatRepo, BookingRepo bookingRepo, ShowRepo showRepo) {
    this.seatRepo = seatRepo;
    this.bookingRepo = bookingRepo;
    this.showRepo = showRepo;
  }

  @Override
  public SeatAvailabilityResponseDTO seatAvailability(UUID showId) {
    Show show =
        showRepo.findById(showId).orElseThrow(() -> new NotFoundException("Show not found"));
    UUID hallId = show.getHall().getId();

    List<Seat> allSeats = seatRepo.findByHallId(hallId);
    List<Booking> booked = bookingRepo.findByShowIdAndStatus(showId, BookingStatus.CONFIRMED);

    Set<UUID> bookedSet = new HashSet<>();
    for (Booking b : booked) {
      bookedSet.add(b.getSeat().getId());
    }

    List<Seat> occupiedSeats = new ArrayList<>();
    List<Seat> emptySeat = new ArrayList<>();

    for (Seat s : allSeats) {
      if (bookedSet.contains(s.getId())) {
        occupiedSeats.add(s);
      } else {
        emptySeat.add(s);
      }
    }
    return new SeatAvailabilityResponseDTO(emptySeat, occupiedSeats);
  }
}

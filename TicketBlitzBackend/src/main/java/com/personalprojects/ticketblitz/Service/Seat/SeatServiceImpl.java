package com.personalprojects.ticketblitz.Service.Seat;

import com.personalprojects.ticketblitz.Entity.Seat;
import com.personalprojects.ticketblitz.Repository.SeatRepo;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class SeatServiceImpl implements SeatService {

  private final SeatRepo seatRepo;

  public SeatServiceImpl(SeatRepo seatRepo) {
    this.seatRepo = seatRepo;
  }

  @Override
  public List<Seat> createSeats(List<Seat> seats) {
    return seatRepo.saveAll(seats);
  }

  @Override
  public List<Seat> getSeatsByHall(UUID hallId) {
    return seatRepo.findByHallId(hallId);
  }
}

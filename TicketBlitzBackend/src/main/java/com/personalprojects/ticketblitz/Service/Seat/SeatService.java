package com.personalprojects.ticketblitz.Service.Seat;

import com.personalprojects.ticketblitz.Entity.Seat;

import java.util.List;
import java.util.UUID;

public interface SeatService {
    List<Seat> createSeats(List<Seat> seats);
    List<Seat> getSeatsByHall(UUID hallId);
}

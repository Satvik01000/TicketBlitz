package com.personalprojects.ticketblitz.Repository;

import com.personalprojects.ticketblitz.Entity.Seat;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepo extends JpaRepository<Seat, UUID> {
  List<Seat> findByHallId(UUID hallId);
}

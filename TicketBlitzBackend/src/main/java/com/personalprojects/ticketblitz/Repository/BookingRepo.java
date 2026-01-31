package com.personalprojects.ticketblitz.Repository;

import com.personalprojects.ticketblitz.Entity.Booking;
import com.personalprojects.ticketblitz.Entity.BookingStatus;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepo extends JpaRepository<Booking, UUID> {
  List<Booking> findByShowIdAndStatus(UUID showId, BookingStatus status);
}

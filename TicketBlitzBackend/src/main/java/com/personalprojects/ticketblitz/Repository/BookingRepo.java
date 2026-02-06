package com.personalprojects.ticketblitz.Repository;

import com.personalprojects.ticketblitz.Entity.Booking;
import com.personalprojects.ticketblitz.Entity.BookingStatus;
import com.personalprojects.ticketblitz.Entity.Seat;
import com.personalprojects.ticketblitz.Entity.Show;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepo extends JpaRepository<Booking, UUID> {
  List<Booking> findByShowIdAndStatus(UUID showId, BookingStatus status);

  boolean existsByShowAndSeatAndStatus(Show show, Seat seat, BookingStatus bookingStatus);

  List<Booking> findAllByStatusAndBookedAtBefore(BookingStatus status, LocalDateTime dateTime);
}

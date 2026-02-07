package com.personalprojects.ticketblitz.Repository;

import com.personalprojects.ticketblitz.Entity.Booking;
import com.personalprojects.ticketblitz.Entity.BookingStatus;
import com.personalprojects.ticketblitz.Entity.Seat;
import com.personalprojects.ticketblitz.Entity.Show;
import io.lettuce.core.dynamic.annotation.Param;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookingRepo extends JpaRepository<Booking, UUID> {
  List<Booking> findByShowIdAndStatus(UUID showId, BookingStatus status);

  @Query(
      "SELECT b.seat "
          + "FROM Booking b "
          + "WHERE b.show.id=:showId "
          + "AND b.status IN ('CONFIRMED', 'PENDING')")
  List<Seat> findBookedSeatsByShowId(@Param("showId") UUID showId);

  boolean existsByShowAndSeatAndStatus(Show show, Seat seat, BookingStatus bookingStatus);

  List<Booking> findAllByStatusAndBookedAtBefore(BookingStatus status, LocalDateTime dateTime);
}

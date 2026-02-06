package com.personalprojects.ticketblitz.Service.Booking;

import com.personalprojects.ticketblitz.DTO.Request.BookingCreationRequestDTO;
import com.personalprojects.ticketblitz.Entity.Booking;
import java.util.UUID;
import org.springframework.transaction.annotation.Transactional;

public interface BookingService {
  Booking createBooking(BookingCreationRequestDTO bookingCreationRequestDTO);

  @Transactional
  void confirmBooking(UUID bookingId);

  @Transactional
  void cancelBooking(UUID bookingId);
}

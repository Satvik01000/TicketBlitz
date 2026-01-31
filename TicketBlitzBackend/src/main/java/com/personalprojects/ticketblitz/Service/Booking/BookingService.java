package com.personalprojects.ticketblitz.Service.Booking;

import com.personalprojects.ticketblitz.DTO.Request.BookingCreationRequestDTO;
import com.personalprojects.ticketblitz.Entity.Booking;

public interface BookingService {
  Booking createBooking(BookingCreationRequestDTO bookingCreationRequestDTO);
}

package com.personalprojects.ticketblitz.Service.Booking;

import com.personalprojects.ticketblitz.DTO.Request.BookingCreationRequestDTO;
import com.personalprojects.ticketblitz.Entity.Booking;

import java.util.UUID;

public interface BookingService {
    Booking createBooking(BookingCreationRequestDTO bookingCreationRequestDTO);
}

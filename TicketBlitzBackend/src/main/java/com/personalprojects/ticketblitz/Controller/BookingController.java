package com.personalprojects.ticketblitz.Controller;

import com.personalprojects.ticketblitz.DTO.Request.BookingCreationRequestDTO;
import com.personalprojects.ticketblitz.Entity.Booking;
import com.personalprojects.ticketblitz.Service.Booking.BookingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/booking")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public Booking createBooking(@RequestBody BookingCreationRequestDTO bookingCreationRequestDTO){
        return bookingService.createBooking(bookingCreationRequestDTO);
    }
}

package com.personalprojects.ticketblitz.Controller;

import com.personalprojects.ticketblitz.DTO.Request.BookingCreationRequestDTO;
import com.personalprojects.ticketblitz.DTO.Response.BookingResponseDTO;
import com.personalprojects.ticketblitz.DTO.Response.PaymentLinkResponseDTO;
import com.personalprojects.ticketblitz.Entity.Booking;
import com.personalprojects.ticketblitz.Service.Booking.BookingService;
import com.personalprojects.ticketblitz.Service.Payment.PaymentService;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

  private final BookingService bookingService;
  private final PaymentService paymentService;

  public BookingController(BookingService bookingService, PaymentService paymentService) {
    this.bookingService = bookingService;
    this.paymentService = paymentService;
  }

  @PostMapping
  public ResponseEntity<BookingResponseDTO> createBooking(
      @RequestBody BookingCreationRequestDTO dto) {
    Booking booking = bookingService.createBooking(dto);
    PaymentLinkResponseDTO paymentUrl =
        paymentService.initiatePayment(booking.getId(), booking.getSeat().getPrice());
    BookingResponseDTO response =
        new BookingResponseDTO(
            booking.getId(), booking.getStatus(), paymentUrl.paymentUrl, paymentUrl.cancelUrl);
    return ResponseEntity.ok(response);
  }

  @PostMapping("/confirm/{bookingId}")
  public ResponseEntity<String> confirmBooking(@PathVariable UUID bookingId) {
    bookingService.confirmBooking(bookingId);
    return ResponseEntity.ok("Payment Successful. Seat booked");
  }

  @PostMapping("/cancel/{bookingId}")
  public ResponseEntity<String> cancelBooking(@PathVariable UUID bookingId) {
    bookingService.cancelBooking(bookingId);
    return ResponseEntity.ok("Booking Cancelled. Seat released.");
  }
}

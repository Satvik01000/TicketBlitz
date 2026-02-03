package com.personalprojects.ticketblitz.Service.Booking;

import com.personalprojects.ticketblitz.DTO.Request.BookingCreationRequestDTO;
import com.personalprojects.ticketblitz.Entity.*;
import com.personalprojects.ticketblitz.Exceptions.NotFoundException;
import com.personalprojects.ticketblitz.Exceptions.SeatAlreadyBookedException;
import com.personalprojects.ticketblitz.Repository.BookingRepo;
import com.personalprojects.ticketblitz.Repository.SeatRepo;
import com.personalprojects.ticketblitz.Repository.ShowRepo;
import com.personalprojects.ticketblitz.Repository.UserRepo;
import java.util.UUID;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookingServiceImple implements BookingService {
  private final BookingRepo bookingRepo;
  private final UserRepo userRepo;
  private final ShowRepo showRepo;
  private final SeatRepo seatRepo;

  public BookingServiceImple(
      BookingRepo bookingRepo, UserRepo userRepo, ShowRepo showRepo, SeatRepo seatRepo) {
    this.bookingRepo = bookingRepo;
    this.userRepo = userRepo;
    this.showRepo = showRepo;
    this.seatRepo = seatRepo;
  }

  @Override
  @Transactional
  public Booking createBooking(BookingCreationRequestDTO dto) {
    UUID userId = dto.getUserId();
    UUID showId = dto.getShowId();
    UUID seatId = dto.getSeatId();
    User user =
        userRepo.findById(userId).orElseThrow(() -> new NotFoundException("User not Found"));
    Show show =
        showRepo.findById(showId).orElseThrow(() -> new NotFoundException("Show not Found"));
    Seat seat =
        seatRepo.findById(seatId).orElseThrow(() -> new NotFoundException("Seat not Found"));
    try {
      Booking booking = new Booking(user, show, seat, BookingStatus.CONFIRMED);
      return bookingRepo.save(booking);
    } catch (DataIntegrityViolationException e) {
      throw new SeatAlreadyBookedException("Seat already booked for this show");
    }
  }
}

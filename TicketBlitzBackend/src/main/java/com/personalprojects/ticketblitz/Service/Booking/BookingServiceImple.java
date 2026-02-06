package com.personalprojects.ticketblitz.Service.Booking;

import com.personalprojects.ticketblitz.DTO.Request.BookingCreationRequestDTO;
import com.personalprojects.ticketblitz.Entity.*;
import com.personalprojects.ticketblitz.Exceptions.NotFoundException;
import com.personalprojects.ticketblitz.Exceptions.SeatAlreadyBookedException;
import com.personalprojects.ticketblitz.Repository.BookingRepo;
import com.personalprojects.ticketblitz.Repository.SeatRepo;
import com.personalprojects.ticketblitz.Repository.ShowRepo;
import com.personalprojects.ticketblitz.Repository.UserRepo;
import com.personalprojects.ticketblitz.Service.RedisLockService;
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
  private final RedisLockService redisLockService;

  public BookingServiceImple(
      BookingRepo bookingRepo,
      UserRepo userRepo,
      ShowRepo showRepo,
      SeatRepo seatRepo,
      RedisLockService redisLockService) {
    this.bookingRepo = bookingRepo;
    this.userRepo = userRepo;
    this.showRepo = showRepo;
    this.seatRepo = seatRepo;
    this.redisLockService = redisLockService;
  }

  @Override
  @Transactional
  public Booking createBooking(BookingCreationRequestDTO dto) {
    UUID showId = dto.getShowId();
    UUID seatId = dto.getSeatId();
    UUID userId = dto.getUserId();

    boolean acquired = redisLockService.acquireLock(showId, seatId, userId);

    if (!acquired) {
      throw new SeatAlreadyBookedException("This seat is being processed.");
    }

    try {
      User user =
          userRepo.findById(userId).orElseThrow(() -> new NotFoundException("User not Found"));
      Show show =
          showRepo.findById(showId).orElseThrow(() -> new NotFoundException("Show not Found"));
      Seat seat =
          seatRepo.findById(seatId).orElseThrow(() -> new NotFoundException("Seat not Found"));

      Booking booking = new Booking(user, show, seat, BookingStatus.PENDING);
      return bookingRepo.saveAndFlush(booking);

    } catch (Exception e) {
      redisLockService.releaseLock(showId, seatId, userId);

      if (e instanceof DataIntegrityViolationException) {
        throw new SeatAlreadyBookedException("Seat is already booked.");
      }

      throw e;
    }
  }

  @Transactional
  @Override
  public void confirmBooking(UUID bookingId) {
    Booking booking =
        bookingRepo
            .findById(bookingId)
            .orElseThrow(() -> new NotFoundException("Booking not found"));

    if (booking.getStatus() == BookingStatus.CONFIRMED) {
      return;
    }

    booking.setStatus(BookingStatus.CONFIRMED);
    bookingRepo.save(booking);

    redisLockService.releaseLock(
        booking.getShow().getId(), booking.getSeat().getId(), booking.getUser().getId());
  }

  @Transactional
  @Override
  public void cancelBooking(UUID bookingId) {
    Booking booking =
        bookingRepo
            .findById(bookingId)
            .orElseThrow(() -> new NotFoundException("Booking not found"));

    if (booking.getStatus() == BookingStatus.CONFIRMED) {
      return;
    }

    bookingRepo.deleteById(bookingId);

    redisLockService.releaseLock(
        booking.getShow().getId(), booking.getSeat().getId(), booking.getUser().getId());
  }
}

package com.personalprojects.ticketblitz.Service.Booking;

import com.personalprojects.ticketblitz.DTO.Request.BookingCreationRequestDTO;
import com.personalprojects.ticketblitz.Entity.*;
import com.personalprojects.ticketblitz.Exceptions.NotFoundException;
import com.personalprojects.ticketblitz.Exceptions.SeatAlreadyBookedException;
import com.personalprojects.ticketblitz.Repository.BookingRepo;
import com.personalprojects.ticketblitz.Repository.SeatRepo;
import com.personalprojects.ticketblitz.Repository.ShowRepo;
import com.personalprojects.ticketblitz.Repository.UserRepo;
import com.personalprojects.ticketblitz.Service.RedisCacheService;
import com.personalprojects.ticketblitz.Service.RedisLockService;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookingServiceImple implements BookingService {

  private final BookingRepo bookingRepo;

  private final UserRepo userRepo;

  private final ShowRepo showRepo;

  private final SeatRepo seatRepo;

  private final RedisLockService redisLockService;

  private final RedisCacheService redisCacheService;

  public BookingServiceImple(
      BookingRepo bookingRepo,
      UserRepo userRepo,
      ShowRepo showRepo,
      SeatRepo seatRepo,
      RedisLockService redisLockService,
      RedisCacheService redisCacheService) {

    this.bookingRepo = bookingRepo;

    this.userRepo = userRepo;

    this.showRepo = showRepo;

    this.seatRepo = seatRepo;

    this.redisLockService = redisLockService;

    this.redisCacheService = redisCacheService;
  }

  @Override
  public Booking createBooking(BookingCreationRequestDTO dto) {
    UUID showId = dto.getShowId();
    UUID seatId = dto.getSeatId();
    UUID userId = dto.getUserId();

    boolean acquired = redisLockService.acquireLock(showId, seatId, userId);
    if (!acquired) {
      throw new SeatAlreadyBookedException("Seat is locked by another user.");
    }

    try {
      return createBookingTransactional(userId, showId, seatId);
    } catch (Exception e) {
      redisLockService.releaseLock(showId, seatId, userId);
      throw e;
    }
  }

  @Transactional
  public Booking createBookingTransactional(UUID userId, UUID showId, UUID seatId) {

    User user =
        userRepo.findById(userId).orElseThrow(() -> new NotFoundException("User not Found"));

    Show show =
        showRepo.findById(showId).orElseThrow(() -> new NotFoundException("Show not Found"));

    Seat seat =
        seatRepo.findById(seatId).orElseThrow(() -> new NotFoundException("Seat not Found"));

    Booking booking = new Booking(user, show, seat, BookingStatus.PENDING);

    Booking savedBooking = bookingRepo.saveAndFlush(booking);

    redisCacheService.cacheBookedSeat(showId, seatId);

    return savedBooking;
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

    redisCacheService.removeBookedSeat(booking.getShow().getId(), booking.getSeat().getId());

    redisLockService.releaseLock(
        booking.getShow().getId(), booking.getSeat().getId(), booking.getUser().getId());
  }
}

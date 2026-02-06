package com.personalprojects.ticketblitz.Scheduler;

import com.personalprojects.ticketblitz.Entity.Booking;
import com.personalprojects.ticketblitz.Entity.BookingStatus;
import com.personalprojects.ticketblitz.Repository.BookingRepo;
import com.personalprojects.ticketblitz.Service.RedisLockService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class BookingCleanupScheduler {

  private final BookingRepo bookingRepo;
  private final RedisLockService redisLockService;

  public BookingCleanupScheduler(BookingRepo bookingRepo, RedisLockService redisLockService) {
    this.bookingRepo = bookingRepo;
    this.redisLockService = redisLockService;
  }

  @Scheduled(fixedRate = 60000)
  @Transactional
  public void cleanupExpiredBookings() {
    // 1. Define "Old": Any PENDING booking created more than 10 minutes ago
    // This MUST match or exceed your Redis TTL (10 mins)
    LocalDateTime cutOffTime = LocalDateTime.now().minusMinutes(10);

    // 2. Find the ghosts
    List<Booking> expiredBookings =
        bookingRepo.findAllByStatusAndBookedAtBefore(BookingStatus.PENDING, cutOffTime);

    if (!expiredBookings.isEmpty()) {
      System.out.println(
          "Janitor found " + expiredBookings.size() + " expired bookings. Cleaning up...");
    }

    for (Booking booking : expiredBookings) {
      bookingRepo.delete(booking);

      redisLockService.releaseLock(
          booking.getShow().getId(), booking.getSeat().getId(), booking.getUser().getId());
    }
  }
}

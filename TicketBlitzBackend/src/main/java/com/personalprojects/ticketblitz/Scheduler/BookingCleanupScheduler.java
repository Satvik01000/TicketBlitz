package com.personalprojects.ticketblitz.Scheduler;

import com.personalprojects.ticketblitz.Entity.Booking;
import com.personalprojects.ticketblitz.Entity.BookingStatus;
import com.personalprojects.ticketblitz.Repository.BookingRepo;
import com.personalprojects.ticketblitz.Service.RedisCacheService;
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
  private final RedisCacheService redisCacheService;

  public BookingCleanupScheduler(
      BookingRepo bookingRepo,
      RedisLockService redisLockService,
      RedisCacheService redisCacheService) {
    this.bookingRepo = bookingRepo;
    this.redisLockService = redisLockService;
    this.redisCacheService = redisCacheService;
  }

  @Scheduled(fixedRate = 60000)
  @Transactional
  public void cleanupExpiredBookings() {
    LocalDateTime cutOffTime = LocalDateTime.now().minusMinutes(10);

    List<Booking> expiredBookings =
        bookingRepo.findAllByStatusAndBookedAtBefore(BookingStatus.PENDING, cutOffTime);

    if (!expiredBookings.isEmpty()) {
      System.out.println(
          "🧹 Janitor found " + expiredBookings.size() + " expired bookings. Cleaning up...");
    }

    for (Booking booking : expiredBookings) {
      bookingRepo.delete(booking);

      redisCacheService.removeBookedSeat(booking.getShow().getId(), booking.getSeat().getId());

      redisLockService.releaseLock(
          booking.getShow().getId(), booking.getSeat().getId(), booking.getUser().getId());
    }
  }
}

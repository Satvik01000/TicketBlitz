package com.personalprojects.ticketblitz.Entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"show_id", "seat_id"})})
@NoArgsConstructor
@AllArgsConstructor
public class Booking extends BaseModel {

  @ManyToOne private User user;

  @ManyToOne private Show show;

  @ManyToOne private Seat seat;

  @Enumerated(EnumType.STRING)
  private BookingStatus status;

  private LocalDateTime bookedAt;

  public Booking(User user, Show show, Seat seat, BookingStatus status) {
    this.user = user;
    this.show = show;
    this.seat = seat;
    this.status = status;
  }

  @PrePersist
  protected void onCreate() {
    this.bookedAt = LocalDateTime.now();
  }
}

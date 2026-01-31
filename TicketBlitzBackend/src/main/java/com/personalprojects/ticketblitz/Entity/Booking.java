package com.personalprojects.ticketblitz.Entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"show_id", "seat_id"})})
public class Booking extends BaseModel {

  @ManyToOne
  private User user;

  @ManyToOne
  private Show show;

  @ManyToOne
  private Seat seat;

  @Enumerated(EnumType.STRING)
  private BookingStatus status;

  private LocalDateTime bookedAt;
}

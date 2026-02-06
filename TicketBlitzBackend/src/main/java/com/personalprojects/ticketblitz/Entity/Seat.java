package com.personalprojects.ticketblitz.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Seat extends BaseModel {
  private String seatNumber;
  private Double price;

  @Enumerated(EnumType.STRING)
  private SeatType seatType;

  @ManyToOne private Hall hall;
}

package com.personalprojects.ticketblitz.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Seat extends BaseModel {
  private String seatNumber;
  private Double price;

  @Enumerated(EnumType.STRING)
  private SeatType seatType;

  @ManyToOne @JsonBackReference private Hall hall;
}

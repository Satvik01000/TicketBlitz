package com.personalprojects.ticketblitz.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class Hall extends BaseModel {
  private int hallNumber;

  @ManyToOne private Cinema cinema;

  @OneToMany(mappedBy = "hall", cascade = CascadeType.ALL)
  List<Seat> seats;
}

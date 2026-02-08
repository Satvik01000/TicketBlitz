package com.personalprojects.ticketblitz.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Hall extends BaseModel {
  private int hallNumber;

  @ManyToOne @JsonBackReference private Cinema cinema;

  @OneToMany(mappedBy = "hall", cascade = CascadeType.ALL)
  @JsonManagedReference
  private List<Seat> seats;
}

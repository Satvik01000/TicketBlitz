package com.personalprojects.ticketblitz.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class Cinema extends BaseModel {
  private String cinemaName;
  private String contactNumber;

  @OneToMany(mappedBy = "cinema", cascade = CascadeType.ALL)
  private List<Hall> hallList;
}

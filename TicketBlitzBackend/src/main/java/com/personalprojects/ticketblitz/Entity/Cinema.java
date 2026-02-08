package com.personalprojects.ticketblitz.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
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
public class Cinema extends BaseModel {
  private String cinemaName;
  private String contactNumber;

  @OneToMany(mappedBy = "cinema", cascade = CascadeType.ALL)
  @JsonManagedReference
  private List<Hall> hallList;
}

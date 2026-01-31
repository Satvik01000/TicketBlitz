package com.personalprojects.ticketblitz.Entity;

import jakarta.persistence.Entity;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Movie extends BaseModel {
  private String title;

  private String description;

  private int durationMinutes;

  private String language;

  private LocalDate releaseDate;

  private String genre;

  private double rating;

  private String posterUrl;
}

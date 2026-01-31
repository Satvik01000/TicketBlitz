package com.personalprojects.ticketblitz.Entity;

import jakarta.persistence.Entity;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

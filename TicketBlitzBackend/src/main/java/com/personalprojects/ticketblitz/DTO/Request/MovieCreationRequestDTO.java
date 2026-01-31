package com.personalprojects.ticketblitz.DTO.Request;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieCreationRequestDTO {
  private String title;

  private String description;

  private int durationMinutes;

  private String language;

  private LocalDate releaseDate;

  private String genre;

  private double rating;

  private String posterUrl;
}

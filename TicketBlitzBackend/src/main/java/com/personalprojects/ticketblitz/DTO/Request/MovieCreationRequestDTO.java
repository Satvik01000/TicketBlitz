package com.personalprojects.ticketblitz.DTO.Request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

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

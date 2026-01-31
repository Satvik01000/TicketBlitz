package com.personalprojects.ticketblitz.Service.Movie;

import com.personalprojects.ticketblitz.DTO.Request.MovieCreationRequestDTO;
import com.personalprojects.ticketblitz.Entity.Movie;
import java.util.List;
import java.util.UUID;

public interface MovieService {
  Movie addMovie(MovieCreationRequestDTO dto);

  Movie updateMovie(UUID movieId, MovieCreationRequestDTO dto);

  void deleteMovie(UUID movieId);

  Movie getMovieById(UUID movieId);

  List<Movie> getAllMovies();
}

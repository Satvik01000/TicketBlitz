package com.personalprojects.ticketblitz.Controller;

import com.personalprojects.ticketblitz.DTO.Request.MovieCreationRequestDTO;
import com.personalprojects.ticketblitz.Entity.Movie;
import com.personalprojects.ticketblitz.Service.Movie.MovieService;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movie")
public class MovieController {

  private final MovieService movieService;

  public MovieController(MovieService movieService) {
    this.movieService = movieService;
  }

  @PostMapping
  public ResponseEntity<Movie> addMovie(@RequestBody MovieCreationRequestDTO dto) {
    return ResponseEntity.status(HttpStatus.CREATED)
            .body(movieService.addMovie(dto));
  }

  @PutMapping("/{movieId}")
  public ResponseEntity<Movie> updateMovie(
          @PathVariable UUID movieId,
          @RequestBody MovieCreationRequestDTO dto
  ) {
    return ResponseEntity.ok(movieService.updateMovie(movieId, dto));
  }

  @DeleteMapping("/{movieId}")
  public ResponseEntity<Void> deleteMovie(@PathVariable UUID movieId) {
    movieService.deleteMovie(movieId);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{movieId}")
  public ResponseEntity<Movie> getMovie(@PathVariable UUID movieId) {
    return ResponseEntity.ok(movieService.getMovieById(movieId));
  }

  @GetMapping
  public ResponseEntity<List<Movie>> getAllMovies() {
    return ResponseEntity.ok(movieService.getAllMovies());
  }
}

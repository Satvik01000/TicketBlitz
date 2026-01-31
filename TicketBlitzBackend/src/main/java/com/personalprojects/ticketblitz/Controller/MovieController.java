package com.personalprojects.ticketblitz.Controller;

import com.personalprojects.ticketblitz.DTO.Request.MovieCreationRequestDTO;
import com.personalprojects.ticketblitz.Entity.Movie;
import com.personalprojects.ticketblitz.Service.Movie.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/movie")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    public Movie addMovie(@RequestBody MovieCreationRequestDTO dto) {
        return movieService.addMovie(dto);
    }

    @PutMapping("/{movieId}")
    public Movie updateMovie( @PathVariable UUID movieId, @RequestBody MovieCreationRequestDTO dto) {
        return movieService.updateMovie(movieId, dto);
    }

    @DeleteMapping("/{movieId}")
    public void deleteMovie(@PathVariable UUID movieId) {
        movieService.deleteMovie(movieId);
    }

    @GetMapping("/{movieId}")
    public Movie getMovie(@PathVariable UUID movieId) {
        return movieService.getMovieById(movieId);
    }

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }
}

package com.personalprojects.ticketblitz.Service.Movie;

import com.personalprojects.ticketblitz.DTO.Request.MovieCreationRequestDTO;
import com.personalprojects.ticketblitz.Entity.Movie;
import com.personalprojects.ticketblitz.Repository.MovieRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MovieServiceImple implements MovieService {

    private final MovieRepo movieRepo;

    public MovieServiceImple(MovieRepo movieRepo) {
        this.movieRepo = movieRepo;
    }

    @Override
    public Movie addMovie(MovieCreationRequestDTO dto) {

        Movie movie = new Movie();
        mapDtoToMovie(dto, movie);

        return movieRepo.save(movie);
    }

    @Override
    public Movie updateMovie(UUID movieId, MovieCreationRequestDTO dto) {

        Movie movie = movieRepo.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        mapDtoToMovie(dto, movie);

        return movieRepo.save(movie);
    }

    @Override
    public void deleteMovie(UUID movieId) {
        if (!movieRepo.existsById(movieId)) {
            throw new RuntimeException("Movie not found");
        }

        movieRepo.deleteById(movieId);
    }

    @Override
    public Movie getMovieById(UUID movieId) {

        return movieRepo.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepo.findAll();
    }

    private void mapDtoToMovie(MovieCreationRequestDTO dto, Movie movie) {
        movie.setTitle(dto.getTitle());
        movie.setDescription(dto.getDescription());
        movie.setDurationMinutes(dto.getDurationMinutes());
        movie.setLanguage(dto.getLanguage());
        movie.setReleaseDate(dto.getReleaseDate());
        movie.setGenre(dto.getGenre());
        movie.setRating(dto.getRating());
        movie.setPosterUrl(dto.getPosterUrl());
    }
}

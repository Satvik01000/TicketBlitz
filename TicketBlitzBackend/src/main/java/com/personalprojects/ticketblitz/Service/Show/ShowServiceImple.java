package com.personalprojects.ticketblitz.Service.Show;

import com.personalprojects.ticketblitz.DTO.Request.ShowCreationRequestDTO;
import com.personalprojects.ticketblitz.Entity.Hall;
import com.personalprojects.ticketblitz.Entity.Movie;
import com.personalprojects.ticketblitz.Entity.Show;
import com.personalprojects.ticketblitz.Exceptions.NotFoundException;
import com.personalprojects.ticketblitz.Repository.HallRepo;
import com.personalprojects.ticketblitz.Repository.MovieRepo;
import com.personalprojects.ticketblitz.Repository.ShowRepo;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class ShowServiceImple implements ShowService {
  private final ShowRepo showRepo;
  private final MovieRepo movieRepo;
  private final HallRepo hallRepo;

  public ShowServiceImple(ShowRepo showRepo, MovieRepo movieRepo, HallRepo hallRepo) {
    this.showRepo = showRepo;
    this.movieRepo = movieRepo;
    this.hallRepo = hallRepo;
  }

  @Override
  public Show createShow(ShowCreationRequestDTO dto) {
    Movie movie = movieRepo.findById(dto.getMovieId())
            .orElseThrow(() -> new NotFoundException("Movie not found"));

    Hall hall = hallRepo.findById(dto.getHallId())
            .orElseThrow(() -> new NotFoundException("Hall not found"));

    Show show = new Show();
    show.setMovie(movie);
    show.setHall(hall);
    show.setStartTime(dto.getStartTime());
    show.setEndTime(dto.getEndTime());

    return showRepo.save(show);
  }

  @Override
  public Show getShowById(UUID showId) {
    return showRepo.findById(showId).orElseThrow(() -> new NotFoundException("Show not found"));
  }

  @Override
  public List<Show> getShowsByMovie(UUID movieId) {
    return showRepo.findByMovieId(movieId);
  }

  @Override
  public List<Show> getAllShows() {
    return showRepo.findAll();
  }
}

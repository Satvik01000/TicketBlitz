package com.personalprojects.ticketblitz.Controller;

import com.personalprojects.ticketblitz.DTO.Request.ShowCreationRequestDTO;
import com.personalprojects.ticketblitz.Entity.Show;
import com.personalprojects.ticketblitz.Service.Show.ShowService;
import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/show")
public class ShowController {

  private final ShowService showService;

  public ShowController(ShowService showService) {
    this.showService = showService;
  }

  @PostMapping
  public Show createShow(@RequestBody ShowCreationRequestDTO dto) {
    return showService.createShow(dto);
  }

  @GetMapping("/{showId}")
  public Show getShow(@PathVariable UUID showId) {
    return showService.getShowById(showId);
  }

  @GetMapping("/movie/{movieId}")
  public List<Show> getShowsByMovie(@PathVariable UUID movieId) {
    return showService.getShowsByMovie(movieId);
  }

  @GetMapping
  public List<Show> getAllShows() {
    return showService.getAllShows();
  }
}

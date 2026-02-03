package com.personalprojects.ticketblitz.Controller;

import com.personalprojects.ticketblitz.DTO.Request.ShowCreationRequestDTO;
import com.personalprojects.ticketblitz.Entity.Show;
import com.personalprojects.ticketblitz.Service.Show.ShowService;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/show")
public class ShowController {

  private final ShowService showService;

  public ShowController(ShowService showService) {
    this.showService = showService;
  }

  @PostMapping
  public ResponseEntity<Show> createShow(@RequestBody ShowCreationRequestDTO dto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(showService.createShow(dto));
  }

  @GetMapping("/{showId}")
  public ResponseEntity<Show> getShow(@PathVariable UUID showId) {
    return ResponseEntity.ok(showService.getShowById(showId));
  }

  @GetMapping("/movie/{movieId}")
  public ResponseEntity<List<Show>> getShowsByMovie(@PathVariable UUID movieId) {
    return ResponseEntity.ok(showService.getShowsByMovie(movieId));
  }

  @GetMapping
  public ResponseEntity<List<Show>> getAllShows() {
    return ResponseEntity.ok(showService.getAllShows());
  }
}

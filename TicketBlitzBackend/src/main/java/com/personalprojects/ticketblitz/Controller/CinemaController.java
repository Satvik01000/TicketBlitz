package com.personalprojects.ticketblitz.Controller;

import com.personalprojects.ticketblitz.Entity.Cinema;
import com.personalprojects.ticketblitz.Service.Cinema.CinemaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cinema")
public class CinemaController {

  private final CinemaService cinemaService;

  public CinemaController(CinemaService cinemaService) {
    this.cinemaService = cinemaService;
  }

    @PostMapping
    public ResponseEntity<Cinema> create(@RequestBody Cinema cinema) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cinemaService.createCinema(cinema));
    }

    @GetMapping
    public ResponseEntity<List<Cinema>> all() {
        return ResponseEntity.ok(cinemaService.getAllCinemas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cinema> get(@PathVariable UUID id) {
        return ResponseEntity.ok(cinemaService.getCinema(id));
    }
}


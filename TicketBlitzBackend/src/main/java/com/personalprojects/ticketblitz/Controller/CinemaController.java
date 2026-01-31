package com.personalprojects.ticketblitz.Controller;

import com.personalprojects.ticketblitz.Entity.Cinema;
import com.personalprojects.ticketblitz.Service.Cinema.CinemaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cinema")
public class CinemaController {

    private final CinemaService cinemaService;

    public CinemaController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @PostMapping
    public Cinema create(@RequestBody Cinema cinema) {
        return cinemaService.createCinema(cinema);
    }

    @GetMapping
    public List<Cinema> all() {
        return cinemaService.getAllCinemas();
    }

    @GetMapping("/{id}")
    public Cinema get(@PathVariable UUID id) {
        return cinemaService.getCinema(id);
    }
}

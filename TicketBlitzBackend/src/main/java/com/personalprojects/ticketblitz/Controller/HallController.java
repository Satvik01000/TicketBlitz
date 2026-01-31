package com.personalprojects.ticketblitz.Controller;

import com.personalprojects.ticketblitz.Entity.Hall;
import com.personalprojects.ticketblitz.Service.Hall.HallService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/hall")
public class HallController {

    private final HallService hallService;

    public HallController(HallService hallService) {
        this.hallService = hallService;
    }

    @PostMapping
    public ResponseEntity<Hall> create(@RequestBody Hall hall) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(hallService.createHall(hall));
    }

    @GetMapping("/cinema/{cinemaId}")
    public ResponseEntity<List<Hall>> byCinema(@PathVariable UUID cinemaId) {
        return ResponseEntity.ok(hallService.getHallsByCinema(cinemaId));
    }
}

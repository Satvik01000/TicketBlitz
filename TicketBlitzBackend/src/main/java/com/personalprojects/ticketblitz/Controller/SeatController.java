package com.personalprojects.ticketblitz.Controller;

import com.personalprojects.ticketblitz.Entity.Seat;
import com.personalprojects.ticketblitz.Service.Seat.SeatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/seat")
public class SeatController {

    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @PostMapping
    public ResponseEntity<List<Seat>> create(@RequestBody List<Seat> seats) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(seatService.createSeats(seats));
    }

    @GetMapping("/hall/{hallId}")
    public ResponseEntity<List<Seat>> byHall(@PathVariable UUID hallId) {
        return ResponseEntity.ok(seatService.getSeatsByHall(hallId));
    }
}

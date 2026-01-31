package com.personalprojects.ticketblitz.Controller;

import com.personalprojects.ticketblitz.Entity.Seat;
import com.personalprojects.ticketblitz.Service.Seat.SeatService;
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
    public List<Seat> create(@RequestBody List<Seat> seats) {
        return seatService.createSeats(seats);
    }

    @GetMapping("/hall/{hallId}")
    public List<Seat> byHall(@PathVariable UUID hallId) {
        return seatService.getSeatsByHall(hallId);
    }
}

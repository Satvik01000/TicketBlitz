package com.personalprojects.ticketblitz.Service.Cinema;

import com.personalprojects.ticketblitz.Entity.Cinema;
import com.personalprojects.ticketblitz.Exceptions.NotFoundException;
import com.personalprojects.ticketblitz.Repository.CinemaRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CinemaServiceImpl implements CinemaService {

    private final CinemaRepo cinemaRepo;

    public CinemaServiceImpl(CinemaRepo cinemaRepo) {
        this.cinemaRepo = cinemaRepo;
    }

    @Override
    public Cinema createCinema(Cinema cinema) {
        return cinemaRepo.save(cinema);
    }

    @Override
    public List<Cinema> getAllCinemas() {
        return cinemaRepo.findAll();
    }

    @Override
    public Cinema getCinema(UUID id) {
        return cinemaRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Cinema not found"));
    }
}

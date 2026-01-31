package com.personalprojects.ticketblitz.Service.Hall;

import com.personalprojects.ticketblitz.Entity.Hall;
import com.personalprojects.ticketblitz.Repository.HallRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HallServiceImpl implements HallService {

    private final HallRepo hallRepo;

    public HallServiceImpl(HallRepo hallRepo) {
        this.hallRepo = hallRepo;
    }

    @Override
    public Hall createHall(Hall hall) {
        return hallRepo.save(hall);
    }

    @Override
    public List<Hall> getHallsByCinema(UUID cinemaId) {
        return hallRepo.findByCinemaId(cinemaId);
    }
}

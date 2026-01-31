package com.personalprojects.ticketblitz.Service.Cinema;

import com.personalprojects.ticketblitz.Entity.Cinema;
import java.util.List;
import java.util.UUID;

public interface CinemaService {
  Cinema createCinema(Cinema cinema);

  List<Cinema> getAllCinemas();

  Cinema getCinema(UUID id);
}

package com.personalprojects.ticketblitz.Service.Hall;

import com.personalprojects.ticketblitz.Entity.Hall;
import java.util.List;
import java.util.UUID;

public interface HallService {
  Hall createHall(Hall hall);

  List<Hall> getHallsByCinema(UUID cinemaId);
}

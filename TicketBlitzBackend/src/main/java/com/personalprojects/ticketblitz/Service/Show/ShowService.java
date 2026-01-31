package com.personalprojects.ticketblitz.Service.Show;

import com.personalprojects.ticketblitz.DTO.Request.ShowCreationRequestDTO;
import com.personalprojects.ticketblitz.Entity.Show;
import java.util.List;
import java.util.UUID;

public interface ShowService {

  Show createShow(ShowCreationRequestDTO dto);

  Show getShowById(UUID showId);

  List<Show> getShowsByMovie(UUID movieId);

  List<Show> getAllShows();
}

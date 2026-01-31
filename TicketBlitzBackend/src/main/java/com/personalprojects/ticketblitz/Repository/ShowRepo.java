package com.personalprojects.ticketblitz.Repository;

import com.personalprojects.ticketblitz.Entity.Show;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepo extends JpaRepository<Show, UUID> {
  List<Show> findByMovieId(UUID movieId);
}

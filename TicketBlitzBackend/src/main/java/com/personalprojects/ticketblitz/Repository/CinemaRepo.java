package com.personalprojects.ticketblitz.Repository;

import com.personalprojects.ticketblitz.Entity.Cinema;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CinemaRepo extends JpaRepository<Cinema, UUID> {}

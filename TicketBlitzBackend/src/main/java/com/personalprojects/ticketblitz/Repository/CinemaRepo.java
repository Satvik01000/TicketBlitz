package com.personalprojects.ticketblitz.Repository;

import com.personalprojects.ticketblitz.Entity.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CinemaRepo extends JpaRepository<Cinema, UUID> {
}

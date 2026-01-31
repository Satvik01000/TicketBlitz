package com.personalprojects.ticketblitz.Repository;

import com.personalprojects.ticketblitz.Entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MovieRepo extends JpaRepository<Movie, UUID> {
}

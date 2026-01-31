package com.personalprojects.ticketblitz.Repository;

import com.personalprojects.ticketblitz.Entity.Hall;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HallRepo extends JpaRepository<Hall, UUID> {}

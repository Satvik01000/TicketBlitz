package com.personalprojects.ticketblitz.Repository;

import com.personalprojects.ticketblitz.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepo extends JpaRepository<User, UUID> {
}

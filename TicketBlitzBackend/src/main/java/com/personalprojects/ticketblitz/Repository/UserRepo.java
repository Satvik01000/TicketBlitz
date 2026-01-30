package com.personalprojects.ticketblitz.Repository;

import com.personalprojects.ticketblitz.Entity.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, UUID> {
  Optional<User> findUserByUserName(String userName);
}

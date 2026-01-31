package com.personalprojects.ticketblitz.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Show extends BaseModel {

  @ManyToOne private Movie movie;

  @ManyToOne private Hall hall;

  private LocalDateTime startTime;

  private LocalDateTime endTime;
}

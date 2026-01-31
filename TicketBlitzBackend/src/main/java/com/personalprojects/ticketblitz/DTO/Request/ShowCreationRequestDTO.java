package com.personalprojects.ticketblitz.DTO.Request;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShowCreationRequestDTO {
  private UUID movieId;
  private UUID hallId;

  private LocalDateTime startTime;
  private LocalDateTime endTime;
}

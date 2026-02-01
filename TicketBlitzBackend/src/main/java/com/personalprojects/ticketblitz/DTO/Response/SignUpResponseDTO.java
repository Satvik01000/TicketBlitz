package com.personalprojects.ticketblitz.DTO.Response;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpResponseDTO {
  private UUID userId;
  private String name;
  private String username;
  private String email;
}

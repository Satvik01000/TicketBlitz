package com.personalprojects.ticketblitz.DTO.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDTO {
  private String name;
  private String userName;
  private String email;
  private String password;
}

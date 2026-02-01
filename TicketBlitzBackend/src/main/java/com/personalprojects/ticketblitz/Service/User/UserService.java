package com.personalprojects.ticketblitz.Service.User;

import com.personalprojects.ticketblitz.DTO.Request.LoginRequestDTO;
import com.personalprojects.ticketblitz.DTO.Request.SignupRequestDTO;
import com.personalprojects.ticketblitz.DTO.Response.LoginResponseDTO;
import com.personalprojects.ticketblitz.DTO.Response.SignUpResponseDTO;

public interface UserService {
  LoginResponseDTO logIn(LoginRequestDTO requestDTO);

  SignUpResponseDTO signUp(SignupRequestDTO requestDTO);
}

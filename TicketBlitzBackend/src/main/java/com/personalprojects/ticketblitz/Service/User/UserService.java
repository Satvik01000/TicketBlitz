package com.personalprojects.ticketblitz.Service.User;

import com.personalprojects.ticketblitz.DTO.Request.LoginRequestDTO;
import com.personalprojects.ticketblitz.DTO.Response.LoginResponseDTO;
import com.personalprojects.ticketblitz.Entity.User;

public interface UserService {
    LoginResponseDTO logIn(LoginRequestDTO requestDTO);
}

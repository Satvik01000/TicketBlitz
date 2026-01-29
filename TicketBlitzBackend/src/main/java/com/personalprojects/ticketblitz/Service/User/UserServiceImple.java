package com.personalprojects.ticketblitz.Service.User;

import com.personalprojects.ticketblitz.DTO.Request.LoginRequestDTO;
import com.personalprojects.ticketblitz.DTO.Response.LoginResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImple implements UserService{
    @Override
    public LoginResponseDTO logIn(LoginRequestDTO loginRequestDTO) {
        return new LoginResponseDTO();
    }
}

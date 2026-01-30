package com.personalprojects.ticketblitz.Service.User;

import com.personalprojects.ticketblitz.DTO.Request.LoginRequestDTO;
import com.personalprojects.ticketblitz.DTO.Response.LoginResponseDTO;
import com.personalprojects.ticketblitz.Repository.UserRepo;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImple implements UserService {
  private final UserRepo userRepo;
  private final AuthenticationManager authenticationManager;

  public UserServiceImple(UserRepo userRepo, AuthenticationManager authenticationManager) {
    this.userRepo = userRepo;
    this.authenticationManager = authenticationManager;
  }

  @Override
  public LoginResponseDTO logIn(LoginRequestDTO loginRequestDTO) {
    try {
      Authentication authentication =
          authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(
                  loginRequestDTO.getUserName(), loginRequestDTO.getPassword()));

      return new LoginResponseDTO("Success");

    } catch (AuthenticationException e) {
      return new LoginResponseDTO("Fail: " + e.getMessage());
    }
  }
}

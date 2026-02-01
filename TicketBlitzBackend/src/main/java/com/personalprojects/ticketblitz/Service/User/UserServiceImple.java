package com.personalprojects.ticketblitz.Service.User;

import com.personalprojects.ticketblitz.DTO.Request.LoginRequestDTO;
import com.personalprojects.ticketblitz.DTO.Request.SignupRequestDTO;
import com.personalprojects.ticketblitz.DTO.Response.LoginResponseDTO;
import com.personalprojects.ticketblitz.DTO.Response.SignUpResponseDTO;
import com.personalprojects.ticketblitz.Entity.User;
import com.personalprojects.ticketblitz.Exceptions.AlreadyExistsException;
import com.personalprojects.ticketblitz.Repository.UserRepo;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImple implements UserService {
  private final UserRepo userRepo;
  private final AuthenticationManager authenticationManager;
  private final BCryptPasswordEncoder passwordEncoder;

  public UserServiceImple(UserRepo userRepo, AuthenticationManager authenticationManager, BCryptPasswordEncoder passwordEncoder) {
    this.userRepo = userRepo;
    this.authenticationManager = authenticationManager;
      this.passwordEncoder = passwordEncoder;
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

  @Override
  public SignUpResponseDTO signUp(SignupRequestDTO requestDTO) {
    if(userRepo.findUserByUserName(requestDTO.getUserName()).isPresent()) {
      throw new AlreadyExistsException("User already exists");
    }

    User user = new User();
    user.setName(requestDTO.getName());
    user.setUserName(requestDTO.getUserName());
    user.setEmail(requestDTO.getEmail());
    user.setPassword(passwordEncoder.encode(requestDTO.getPassword()));

    userRepo.save(user);
    return new SignUpResponseDTO(
            user.getId(),
            user.getName(),
            user.getUserName(),
            user.getEmail()
    );
  }
}

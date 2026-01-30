package com.personalprojects.ticketblitz.Controller;

import com.personalprojects.ticketblitz.DTO.Request.LoginRequestDTO;
import com.personalprojects.ticketblitz.DTO.Response.LoginResponseDTO;
import com.personalprojects.ticketblitz.Service.User.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/login")
  LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO) {
    return userService.logIn(loginRequestDTO);
  }
}

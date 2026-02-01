package com.personalprojects.ticketblitz.Controller;

import com.personalprojects.ticketblitz.DTO.Request.LoginRequestDTO;
import com.personalprojects.ticketblitz.DTO.Request.SignupRequestDTO;
import com.personalprojects.ticketblitz.DTO.Response.LoginResponseDTO;
import com.personalprojects.ticketblitz.DTO.Response.SignUpResponseDTO;
import com.personalprojects.ticketblitz.Service.User.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/signup")
  public ResponseEntity<SignUpResponseDTO> signup(@RequestBody SignupRequestDTO dto) {
    SignUpResponseDTO response = userService.signUp(dto);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  // TODO Implement RBAC
  // TODO Implement JWT both access and refresh token
  @PostMapping("/login")
  public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO dto) {
    LoginResponseDTO response = userService.logIn(dto);

    if (response.getToken().startsWith("Fail")) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    return ResponseEntity.ok(response);
  }
}

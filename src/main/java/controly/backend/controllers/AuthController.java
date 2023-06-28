package controly.backend.controllers;


import controly.backend.dto.AuthenticationResponse;
import controly.backend.dto.CreateNewUserRequest;
import controly.backend.dto.LoginRequest;
import controly.backend.services.AuthenticationService;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody @Valid CreateNewUserRequest user) {
      return ResponseEntity.ok(authenticationService.register(user));
    }

  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody @Valid LoginRequest user) {
    return ResponseEntity.ok(authenticationService.authenticate(user));
  }
}

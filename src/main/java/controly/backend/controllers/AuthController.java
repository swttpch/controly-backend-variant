package controly.backend.controllers;


import controly.backend.dto.*;
import controly.backend.services.AuthenticationService;
import controly.backend.services.PasswordRecoveryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthenticationService authenticationService;
  private final PasswordRecoveryService passwordRecovery;

  @PostMapping("/register")
  public ResponseEntity<Long> register(@RequestBody @Valid CreateNewUserRequest user) {
    var newUser = authenticationService.register(user);
    return ResponseEntity.status(201).body(newUser.getIdUser());
  }

  @PostMapping("/validate")
  public ResponseEntity<AuthenticationResponse> validate(@RequestBody @Valid ValidateUserRequest user) {
    return ResponseEntity.status(200).body(authenticationService.validateUser(user.getIdUser(), user.getCode()));
  }
  @PostMapping("/revalidate/{id}")
  public ResponseEntity<?> revalidate(@PathVariable Long id) {
    authenticationService.resendValidation(id);
    return ResponseEntity.status(200).build();
  }

  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody @Valid LoginRequest user) {
    return ResponseEntity.ok(authenticationService.authenticate(user));
  }

  @PostMapping("/password-recovery")
  public ResponseEntity<?> passwordRecovery(@RequestBody PasswordRecoveryRequest form) {
    passwordRecovery.passwordRecovery(form);
    return ResponseEntity.status(200).build();
  }

  @GetMapping("/password-recovery")
  public ResponseEntity<Boolean> passwordRecovery(@RequestParam String token) {
    return ResponseEntity.status(200).body(passwordRecovery.isPasswordRecoveryTokenValid(token));
  }

  @PutMapping("/password-recovery")
  public ResponseEntity<?> updatePassword(@RequestParam String token, @RequestBody NewPasswordRequest form) {
    passwordRecovery.updatePassword(token, form);
    return ResponseEntity.status(200).build();
  }
}

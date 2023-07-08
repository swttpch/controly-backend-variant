package controly.backend.services;

import controly.backend.dto.AuthenticationResponse;
import controly.backend.dto.CreateNewUserRequest;
import controly.backend.dto.LoginRequest;
import controly.backend.entities.UserEntity;
import controly.backend.enums.Role;
import controly.backend.exceptions.EmailNotFoundException;
import controly.backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse register(CreateNewUserRequest request) {
    var user = UserEntity.builder()
        .name(request.getName())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .updatedAt(new Date())
        .createdAt(new Date())
        .isActive(true)
        .role(Role.USER)
        .build();
    repository.save(user);
    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse
        .builder()
        .token(jwtToken)
        .build();
  }

  public AuthenticationResponse authenticate(LoginRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
    );
    var user = repository.findByEmail(request.getEmail()).orElseThrow(EmailNotFoundException::new);
    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse
        .builder()
        .token(jwtToken)
        .build();
  }
}

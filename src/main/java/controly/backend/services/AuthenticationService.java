package controly.backend.services;

import controly.backend.dto.AuthenticationResponse;
import controly.backend.dto.CreateNewUserRequest;
import controly.backend.dto.LoginRequest;
import controly.backend.entities.UserEntity;
import controly.backend.enums.Role;
import controly.backend.exceptions.EmailAlreadyExistsException;
import controly.backend.exceptions.EmailNotFoundException;
import controly.backend.exceptions.UserIsDisabledException;
import controly.backend.exceptions.UsersIdNotFould;
import controly.backend.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

import static controly.backend.utils.NumberUtils.getRandomNumberString;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final EmailService emailService;

  public UserEntity register(CreateNewUserRequest request) {
    Optional<UserEntity> optUser = repository.findByEmail(request.getEmail());
    if (optUser.isPresent()) throw new EmailAlreadyExistsException();
    var user = UserEntity.builder()
      .name(request.getName())
      .email(request.getEmail())
      .password(passwordEncoder.encode(request.getPassword()))
      .updatedAt(new Date())
      .createdAt(new Date())
      .isActive(false)
      .role(Role.USER)
      .validationCode(getRandomNumberString())
      .build();
    repository.save(user);
    sendValidationEmail("Confirme seu e-mail e acesse a plataforma! - ControlY", user.getEmail(), user.getValidationCode());
    return user;
  }

  @Transactional
  public void resendValidation(Long idUser) {
    Optional<UserEntity> optUser = repository.findById(idUser);
    if(optUser.isEmpty()) throw new UsersIdNotFould();
    var user = optUser.get();
    user.setValidationCode(getRandomNumberString());
    repository.save(user);
    sendValidationEmail("Confirme seu e-mail e acesse a plataforma! - ControlY", user.getEmail(), user.getValidationCode());
  }

  public void sendValidationEmail(String subject, String email, String validationCode){
    emailService.sendEmail(subject, email, validationCode);
  }

  public AuthenticationResponse validateUser(Long idUser, String code){
    Optional<UserEntity> optUser = repository.findById(idUser);
    if(optUser.isEmpty()) throw new UsersIdNotFould();
    var user = optUser.get();
    if (!code.equals(user.getValidationCode())) throw new UserIsDisabledException();
    else {
      user.setIsActive(true);
      var jwtToken = jwtService.generateToken(user);
      return AuthenticationResponse
        .builder()
        .token(jwtToken)
        .build();
    }
  }

  public AuthenticationResponse authenticate(LoginRequest request) {
    authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
    );
    var user = repository.findByEmail(request.getEmail()).orElseThrow(EmailNotFoundException::new);
    if(!user.getIsActive()) throw new UserIsDisabledException();
    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse
      .builder()
      .token(jwtToken)
      .build();
  }
}


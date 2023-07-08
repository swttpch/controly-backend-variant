package controly.backend.services;

import controly.backend.dto.NewPasswordRequest;
import controly.backend.dto.PasswordRecoveryRequest;
import controly.backend.entities.PasswordRecoveryEntity;
import controly.backend.entities.UserEntity;
import controly.backend.exceptions.EmailNotFoundException;
import controly.backend.exceptions.InvalidTokenException;
import controly.backend.exceptions.PasswordAlreadyChangedException;
import controly.backend.exceptions.PasswordNotMatchException;
import controly.backend.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import static controly.backend.constants.Urls.clientUrl;

@Service
@RequiredArgsConstructor
public class PasswordRecoveryService {
final private EmailService sendEmail;

 final private UserRepository userRepository;
  final private static SecureRandom secureRandom = new SecureRandom();
  final private static Base64.Encoder base64Encoder = Base64.getUrlEncoder();

  private final PasswordEncoder passwordEncoder;

  @Transactional
  public void passwordRecovery(PasswordRecoveryRequest userToRecover){
    UserEntity user = userRepository.findByEmail(userToRecover.getEmail())
      .orElseThrow(EmailNotFoundException::new);
    if (user.getPasswordRecoveryEntity() != null) {
      user.setPasswordRecoveryEntity(null);
      userRepository.save(user);
    }
    Calendar cal = Calendar.getInstance();
    cal.setTime(new Date());
    cal.add(Calendar.HOUR_OF_DAY, 24);
    PasswordRecoveryEntity passwordRecovery =
      PasswordRecoveryEntity.builder()
        .token(generateNewToken())
        .expirationTime(cal.getTime())
        .recoveredIn(new Date())
        .build();
    user.setPasswordRecoveryEntity(passwordRecovery);
    var content = clientUrl + "/password-update?token=" +  user.getPasswordRecoveryEntity().getToken();
    sendEmail.sendEmail(user.getName(),user.getEmail(), content);
  }

  public boolean isPasswordRecoveryTokenValid(String token) {
    PasswordRecoveryEntity passwordRecovery = userRepository.findPasswordRecoveryByPasswordRecoveryEntityToken(token)
      .orElseThrow(InvalidTokenException::new);
    return new Date().compareTo(passwordRecovery.getExpirationTime()) <= 0;
  }

  public void updatePassword(String token, NewPasswordRequest newPassword) {
    UserEntity userEntity = userRepository.findByPasswordRecoveryEntityToken(token)
      .orElseThrow(InvalidTokenException::new);
    if(!isPasswordRecoveryTokenValid(token)) throw new InvalidTokenException();
    if (!newPassword.getConfirmation().equals(newPassword.getPassword())) throw new PasswordNotMatchException();
    userEntity.setPassword(passwordEncoder.encode(newPassword.getPassword()));
    userRepository.save(userEntity);
  }

  public static String generateNewToken() {
    byte[] randomBytes = new byte[24];
    secureRandom.nextBytes(randomBytes);
    return base64Encoder.encodeToString(randomBytes);
  }
}

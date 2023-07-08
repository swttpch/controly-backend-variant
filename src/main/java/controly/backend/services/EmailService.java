package controly.backend.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class EmailService {
  private final JavaMailSender mailSender;
  public void sendEmail(String name, String email, String content) {
    try {
      SimpleMailMessage message = new SimpleMailMessage();
      message.setFrom("suporte.controly@gmail.com");
      message.setTo(email);
      message.setSubject("Solicitação de Troca de Senha - ControlY");
      message.setText(content);
      mailSender.send(message);
    } catch (Exception err) {
      err.printStackTrace();
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something goes wrong.");
    }
  }
}



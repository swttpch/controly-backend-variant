package controly.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class PasswordRecoveryRequest {
  @NotNull
  @Email
  private String email;
}

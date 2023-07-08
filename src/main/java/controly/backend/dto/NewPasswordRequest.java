package controly.backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class NewPasswordRequest {
  @NotNull
  private String password;
  @NotNull
  private String confirmation;
}

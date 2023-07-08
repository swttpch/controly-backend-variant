package controly.backend.entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PasswordRecoveryEntity {

  private String token;

  private Date expirationTime;

  private Date recoveredIn;


}

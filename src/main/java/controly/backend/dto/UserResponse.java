package controly.backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class UserResponse {
  private Long idUser;
  private String name;
  private String nickname;
  private String email;
  private Boolean isActive;
  private Long idGithub;
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private Date disabledIn;
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private Date createdAt;
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private Date updatedAt;
}

package controly.backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import controly.backend.entities.MediaDataEntity;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
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

  private MediaFileResponse profilePicture;
}

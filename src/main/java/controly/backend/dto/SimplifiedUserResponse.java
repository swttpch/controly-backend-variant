package controly.backend.dto;

import lombok.Data;

@Data
public class SimplifiedUserResponse {
    private Long idUser;
    private String name;
    private String nickname;
}

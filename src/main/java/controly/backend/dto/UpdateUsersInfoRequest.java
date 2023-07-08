package controly.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class UpdateUsersInfoRequest {
    @Pattern(regexp = "/^([^0-9]*)$/", message = "Numbers not allowed in name.")
    private String name;
    @Pattern(regexp = "^[A-Za-z][A-Za-z0-9_]{5,14}$", message="Invalid nickname!")
    private String nickname;
    @Email
    private String email;
}

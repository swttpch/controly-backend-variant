package controly.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
@Getter
public class CreateNewUserRequest {
    @NotBlank
    private String name;
    @NotNull
    @Size(min=8)
    private String password;
    @NotNull
    @Email
    private String email;
}

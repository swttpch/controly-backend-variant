package controly.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Password already changed.")
public class PasswordAlreadyChangedException extends RuntimeException{
}

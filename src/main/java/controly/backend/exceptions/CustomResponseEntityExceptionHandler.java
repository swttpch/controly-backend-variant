package controly.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
  @ExceptionHandler(value = {InvalidTokenException.class})
  protected ResponseEntity<Object> handleInvalidTokenException(InvalidTokenException ex, WebRequest request){
    return handleExceptionInternal(ex, ex.getMessage(), null, HttpStatus.BAD_REQUEST, request);
  }

  @ExceptionHandler(value = {EmailAlreadyExistsException.class})
  protected ResponseEntity<Object> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex, WebRequest request){
    return handleExceptionInternal(ex, ex.getMessage(), null, HttpStatus.CONFLICT, request);
  }
  @ExceptionHandler(value = {EmailNotFoundException.class})
  protected ResponseEntity<Object> handleEmailNotFoundException(EmailNotFoundException ex, WebRequest request){
    return handleExceptionInternal(ex, ex.getMessage(), null, HttpStatus.NOT_FOUND, request);
  }

  @ExceptionHandler(value = {NicknameAlreadyExistsException.class})
  protected ResponseEntity<Object> handleNicknameAlreadyExistsException(NicknameAlreadyExistsException ex, WebRequest request){
    return handleExceptionInternal(ex, ex.getMessage(), null, HttpStatus.CONFLICT, request);
  }

  @ExceptionHandler(value = {UserAlreadyActiveException.class})
  protected ResponseEntity<Object> handleUserAlreadyActiveException(UserAlreadyActiveException ex, WebRequest request){
    return handleExceptionInternal(ex, ex.getMessage(), null, HttpStatus.CONFLICT, request);
  }

  @ExceptionHandler(value = {UserAlreadyDisabledException.class})
  protected ResponseEntity<Object> handleUserAlreadyDisabledException(UserAlreadyDisabledException ex, WebRequest request){
    return handleExceptionInternal(ex, ex.getMessage(), null, HttpStatus.CONFLICT, request);
  }

  @ExceptionHandler(value = {UsersIdNotFould.class})
  protected ResponseEntity<Object> handleUsersIdNotFould(UsersIdNotFould ex, WebRequest request){
    return handleExceptionInternal(ex, ex.getMessage(), null, HttpStatus.NOT_FOUND, request);
  }

  @ExceptionHandler(value = {UserIsDisabledException.class})
  protected ResponseEntity<Object> handleUserIsDisabledException(UserIsDisabledException ex, WebRequest request){
    return handleExceptionInternal(ex, ex.getMessage(), null, HttpStatus.CONFLICT, request);
  }

  @ExceptionHandler(value = {PasswordAlreadyChangedException.class})
  protected ResponseEntity<Object> handlePasswordAlreadyChangedException(PasswordAlreadyChangedException ex, WebRequest request){
    return handleExceptionInternal(ex, ex.getMessage(), null, HttpStatus.CONFLICT, request);
  }

  @ExceptionHandler(value = {PasswordNotMatchException.class})
  protected ResponseEntity<Object> handlePasswordNotMatchException(PasswordNotMatchException ex, WebRequest request){
    return handleExceptionInternal(ex, ex.getMessage(), null, HttpStatus.CONFLICT, request);
  }
}

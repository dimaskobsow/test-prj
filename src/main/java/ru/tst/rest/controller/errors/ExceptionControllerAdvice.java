package ru.tst.rest.controller.errors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.tst.rest.dto.errors.ErrorDTO;
import ru.tst.rest.exception.EntityNotFoundException;

@ControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {

    private static final String EXCEPTION_TEXT = "Exception handled: ";

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ErrorDTO> exceptionHandler(Exception ex) {
        log.error(EXCEPTION_TEXT, ex);
        return ResponseEntity.internalServerError().body(
                ErrorDTO.builder()
                        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message(ex.getMessage())
                        .build()
        );
    }

    @ExceptionHandler(InsufficientAuthenticationException.class)
    @ResponseBody
    public ResponseEntity<ErrorDTO> unauthorized(InsufficientAuthenticationException ex) {
        log.error(EXCEPTION_TEXT, ex);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value()).body(
                ErrorDTO.builder()
                        .code(HttpStatus.UNAUTHORIZED.value())
                        .message(ex.getMessage())
                        .build()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<ErrorDTO> badRequest(MethodArgumentNotValidException ex) {
        log.error(EXCEPTION_TEXT, ex);
        return ResponseEntity.badRequest().body(
                ErrorDTO.builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .message(ex.getMessage())
                        .build()
        );
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseBody
    public ResponseEntity<ErrorDTO> notFound(EntityNotFoundException ex) {
        log.error(EXCEPTION_TEXT, ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(
                ErrorDTO.builder()
                        .code(HttpStatus.NOT_FOUND.value())
                        .message(ex.getMessage())
                        .build()
        );
    }
}

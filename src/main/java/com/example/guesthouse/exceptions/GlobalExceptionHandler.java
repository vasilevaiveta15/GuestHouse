package com.example.guesthouse.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler
{
  @ExceptionHandler(InvalidException.class)
  public ResponseEntity<String> handlerInvalidException(InvalidException ex)
  {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({ConstraintViolationException.class})
  public ResponseEntity<Map<String, Object>> handleConstraintExceptions(ConstraintViolationException exception)
  {
    Map<String, Object> exceptedBody = new HashMap<>();
    exceptedBody.put("timestamp: ", LocalDateTime.now());
    for (ConstraintViolation<?> e : exception.getConstraintViolations()) {
      exceptedBody.put("message: ", e.getMessage());
    }
    return new ResponseEntity<>(exceptedBody, HttpStatus.NOT_FOUND);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                HttpStatus status, WebRequest request)
  {
    Map<String, String> validationErrors = new HashMap<>();
    List<ObjectError> objectErrorList = ex.getBindingResult().getAllErrors();
    String fieldOrObject = "";
    String errorMessage;
    for (ObjectError objectError : objectErrorList) {
      if (objectError instanceof FieldError) {
        fieldOrObject = ((FieldError) objectError).getField();
      }
      else if (objectError != null) {
        fieldOrObject = objectError.getObjectName();
      }
      errorMessage = Objects.requireNonNull(objectError).getDefaultMessage();
      validationErrors.put(fieldOrObject, errorMessage);
    }
    return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
  }
}

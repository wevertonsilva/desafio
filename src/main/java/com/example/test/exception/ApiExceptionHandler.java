package com.example.test.exception;

import com.example.test.dto.ExceptionDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;

import static org.springframework.http.HttpStatus.*;

@AllArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDTO> NotFoundException(NotFoundException exception){
        return ResponseEntity.status(NOT_FOUND).body(
                new ExceptionDTO(NOT_FOUND, exception.getMessage(), OffsetDateTime.now())
        );
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ExceptionDTO> UnauthorizedException(UnauthorizedException exception){
        return ResponseEntity.status(UNAUTHORIZED).body(
                new ExceptionDTO(UNAUTHORIZED, exception.getMessage(), OffsetDateTime.now())
        );
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionDTO> BadRequestException(BadRequestException exception){
        return ResponseEntity.status(BAD_REQUEST).body(
                new ExceptionDTO(BAD_REQUEST, exception.getMessage(), OffsetDateTime.now())
        );
    }

}
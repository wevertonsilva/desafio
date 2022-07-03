package com.example.test.exception;

import com.example.test.dto.ExceptionArgumentNotValidDTO;
import com.example.test.dto.ExceptionDTO;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
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

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@AllArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private MessageSource messageSource;

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDTO> notFoundException(NotFoundException exception){
        return ResponseEntity.status(NOT_FOUND).body(
                new ExceptionDTO(NOT_FOUND, exception.getMessage(), OffsetDateTime.now())
        );
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ExceptionDTO> unauthorizedException(UnauthorizedException exception){
        return ResponseEntity.status(UNAUTHORIZED).body(
                new ExceptionDTO(UNAUTHORIZED, exception.getMessage(), OffsetDateTime.now())
        );
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionDTO> badRequestException(BadRequestException exception){
        return ResponseEntity.status(BAD_REQUEST).body(
                new ExceptionDTO(BAD_REQUEST, exception.getMessage(), OffsetDateTime.now())
        );
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ExceptionArgumentNotValidDTO.Warnings> warnings = new ArrayList<>();

        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            String name = ((FieldError) error).getField();
            String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());

            warnings.add(new ExceptionArgumentNotValidDTO.Warnings(name, message));
        }

        ExceptionArgumentNotValidDTO problem = new ExceptionArgumentNotValidDTO();
        problem.setStatus(status);
        problem.setDateTime(OffsetDateTime.now()); // OffsetDateTime = adiciona o UTC no final da data/hora
        problem.setMessage("Um ou mais campos são inválidos. Tente novamente");
        problem.setErros(warnings);

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

}

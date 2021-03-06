package com.example.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
public class ExceptionDTO {

    private HttpStatus status;
    private String message;
    private OffsetDateTime dateTime;

}

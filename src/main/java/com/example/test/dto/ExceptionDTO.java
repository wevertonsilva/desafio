package com.example.test.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
public class ExceptionDTO {

    @ApiModelProperty(notes = "Status da exceção", example = "NOT_FOUND")
    private HttpStatus status;

    @ApiModelProperty(notes = "Mensagem de erro", example = "Usuário não encontrado")
    private String message;

    @ApiModelProperty(notes = "Data e hora do erro", example = "2022-07-01T21:14:17.0827809-03:00")
    private OffsetDateTime dateTime;

}

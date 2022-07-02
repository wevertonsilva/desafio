package com.example.test.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioOutDTO {

    @ApiModelProperty(notes = "Id do usuário", example = "1", required = true)
    private Long id;

    @ApiModelProperty(notes = "Nome da pessoa", example = "Weverton", required = true)
    private String nome;

    @ApiModelProperty(notes = "Email de usuário", example = "weverton@email.com", required = true)
    private String email;

    @ApiModelProperty(notes = "Senha de usuário", example = "senha123", required = true)
    private String accessToken;

}

package com.example.test.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class UsuarioLoginDTO {

    @NotBlank
    @ApiModelProperty(notes = "Email de usuário", example = "weverton@email.com", required = true)
    private String email;

    @NotBlank
    @ApiModelProperty(notes = "Senha de usuário", example = "senha123", required = true)
    private String senha;

}

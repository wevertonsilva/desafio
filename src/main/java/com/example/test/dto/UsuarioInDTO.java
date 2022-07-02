package com.example.test.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioInDTO {

    @NotBlank
    @ApiModelProperty(notes = "Nome da pessoa", example = "Weverton", required = true)
    private String nome;

    @Email
    @NotBlank
    @ApiModelProperty(notes = "Email de usuário", example = "weverton@email.com", required = true)
    private String email;

    @NotBlank
    @ApiModelProperty(notes = "Senha de usuário", example = "Senha.123", required = true)
    private String senha;
}

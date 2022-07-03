package com.example.test.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import static com.example.test.utils.Constants.CAMPO_OBRIGATORIO;
import static com.example.test.utils.Constants.EMAIL_INVALIDO;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioInDTO {

    @NotBlank(message = CAMPO_OBRIGATORIO)
    @ApiModelProperty(notes = "Nome da pessoa", example = "Weverton", required = true)
    private String nome;

    @Email(message = EMAIL_INVALIDO)
    @NotBlank(message = CAMPO_OBRIGATORIO)
    @ApiModelProperty(notes = "Email de usuário", example = "weverton@email.com", required = true)
    private String email;

    @NotBlank(message = CAMPO_OBRIGATORIO)
    @ApiModelProperty(notes = "Senha de usuário", example = "Senha.123", required = true)
    private String senha;
}

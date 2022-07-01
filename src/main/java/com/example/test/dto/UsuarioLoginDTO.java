package com.example.test.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class UsuarioLoginDTO {

    @NotBlank
    private String email;

    @NotBlank
    private String senha;

}

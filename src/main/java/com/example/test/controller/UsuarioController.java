package com.example.test.controller;

import com.example.test.dto.UsuarioInDTO;
import com.example.test.dto.UsuarioLoginDTO;
import com.example.test.dto.UsuarioOutDTO;
import com.example.test.service.UsuarioService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    @ApiOperation(nickname = "persist", value = "Salva um novo usuário ", response = ResponseEntity.class, httpMethod = "POST", authorizations = {@Authorization(value = "Bearer")})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Salva o usuário"),
            @ApiResponse(code = 401, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    public ResponseEntity<UsuarioOutDTO> persist (@RequestBody @Valid UsuarioInDTO dto) {
        return service.persist(dto);
    }

    @PostMapping("/login")
    @ApiOperation(nickname = "login", value = "Realiza o login do usuário", response = ResponseEntity.class, httpMethod = "POST", authorizations = {@Authorization(value = "Bearer")})
    public ResponseEntity<UsuarioOutDTO> login (@RequestBody UsuarioLoginDTO dto) {
        return service.login(dto);
    }

}

package com.example.test.controller;

import com.example.test.dto.TarefaDTO;
import com.example.test.dto.UsuarioOutDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(value = "ALOOOO",description = "ALOOOOO")
@RestController
@RequestMapping("/tarefa")
public class TarefaController {

//    @Autowired
//    private UsuarioService service;

    @PostMapping
    @ApiOperation(nickname = "findAll", value = "get all data", response = String.class,
            httpMethod = "GET", authorizations = {
            @Authorization(value = "Bearer")})
    public ResponseEntity<UsuarioOutDTO> persist (@RequestBody @Valid TarefaDTO dto) throws Exception {
        //return service.persist(dto);
        return null;
    }

}

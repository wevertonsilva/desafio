package com.example.test.controller;

import com.example.test.dto.UsuarioInDTO;
import com.example.test.dto.UsuarioLoginDTO;
import com.example.test.dto.UsuarioOutDTO;
import com.example.test.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    public ResponseEntity<UsuarioOutDTO> persist (@RequestBody @Valid UsuarioInDTO dto) throws Exception {
        return service.persist(dto);
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioOutDTO> login (@RequestBody UsuarioLoginDTO dto) throws Exception {
        return service.login(dto);
    }

}

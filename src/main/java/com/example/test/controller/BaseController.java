package com.example.test.controller;

import com.example.test.dto.UsuarioInfosDTO;

import javax.servlet.http.HttpServletRequest;

public class BaseController {

    public UsuarioInfosDTO getUsuarioInfosByToken(HttpServletRequest request){
        String authorization = request.getHeader("Authorization");
        //TODO decodificar token
        return UsuarioInfosDTO.builder().id(1L).nome("teste").email("teste@teste.com").build();
    }

}

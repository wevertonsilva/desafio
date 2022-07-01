package com.example.test.controller;

import com.example.test.dto.UsuarioInfosDTO;
import com.example.test.exception.UnauthorizedException;
import com.example.test.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

import static com.example.test.utils.Constants.TOKEN_NAO_INFORMADO;
import static java.util.Objects.isNull;

public class BaseController {

    @Autowired
    private TokenService tokenService;

    public UsuarioInfosDTO getUsuarioInfosByToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if(isNull(authorization) || authorization.isBlank())
            throw new UnauthorizedException(TOKEN_NAO_INFORMADO);

        String s = tokenService.decodeToken(authorization);
        String email = s.split("\"sub\":\"")[1].split("\",")[0];
        tokenService.validarExpiracaoToken(email);

        return UsuarioInfosDTO.builder().email(email).build();
    }

}

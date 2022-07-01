package com.example.test.service;

import com.example.test.repository.UsuarioRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.SneakyThrows;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class TokenService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public String generateToken(String email) {
        return Jwts.builder()
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis() + 1800000L))
                .signWith(SignatureAlgorithm.HS256, email)
                .compact();
    }

    @SneakyThrows
    public String decodeToken(String tokenNew) {
        String[] splitString = tokenNew.split("\\.");
        Base64 base64Url = new Base64(true);
        return new String(base64Url.decode(splitString[1]));
    }

}

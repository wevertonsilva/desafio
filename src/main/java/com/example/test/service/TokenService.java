package com.example.test.service;

import com.example.test.exception.NotFoundException;
import com.example.test.exception.UnauthorizedException;
import com.example.test.model.Token;
import com.example.test.model.Usuario;
import com.example.test.repository.UsuarioRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.SneakyThrows;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.Optional;

import static com.example.test.utils.Constants.TOKEN_EXPIRADO;
import static com.example.test.utils.Constants.USUARIO_NAO_ENCONTRADO;

@Service
public class TokenService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Token generateToken(String email) {
        String accessToken = Jwts.builder()
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setSubject(email)
                .setExpiration(new Date(900000L))
                .signWith(SignatureAlgorithm.HS256, email)
                .compact();
        return Token.builder()
                .accessToken(accessToken)
                .expiracao(getExpiracaoToken(accessToken))
                .email(email)
                .build();
    }

    @SneakyThrows
    public String decodeToken(String tokenNew) {
        tokenNew = tokenNew.replace("Bearer ", "");
        String[] splitString = tokenNew.split("\\.");
        Base64 base64Url = new Base64(true);
        return new String(base64Url.decode(splitString[1]));
    }

    public OffsetDateTime getExpiracaoToken(String token) {
        token = token.replace("Bearer ", "");
        String s = decodeToken(token);
        Long segundos = Long.parseLong(s.split("\"exp\":")[1].split("}")[0]);
        return OffsetDateTime.now().plusSeconds(segundos);
    }

    public void validarExpiracaoToken(String email) {
        Optional<Usuario> byEmail = usuarioRepository.findByEmail(email);
        if (byEmail.isEmpty())
            throw new NotFoundException(USUARIO_NAO_ENCONTRADO);

        if (byEmail.get().getToken().getExpiracao().isBefore(OffsetDateTime.now()))
            throw new UnauthorizedException(TOKEN_EXPIRADO);
    }

}
package com.example.test.service;

import com.example.test.exception.NotFoundException;
import com.example.test.exception.UnauthorizedException;
import com.example.test.model.Token;
import com.example.test.model.Usuario;
import com.example.test.repository.TokenRepository;
import com.example.test.repository.UsuarioRepository;
import lombok.Data;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.OffsetDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Data
@ExtendWith(SpringExtension.class)
class TokenServiceTest {

    @InjectMocks
    private TokenService service;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private TokenRepository repository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private Token token;

    @Mock
    private Usuario usuario;

    @DisplayName("generateToken ok")
    @Test
    void generateToken() {
        when(token.getEmail()).thenReturn("teste@teste.com");
        when(repository.findByEmail(token.getEmail())).thenReturn(Optional.of(token));
        when(repository.save(any(Token.class))).thenReturn(token);
        service.generateToken(token.getEmail());
        verify(repository).save(any(Token.class));
    }

    @DisplayName("generateToken no primeiro login")
    @Test
    void generateTokenPrimeiroLogin() {
        when(token.getEmail()).thenReturn("teste@teste.com");
        when(repository.findByEmail(token.getEmail())).thenReturn(Optional.empty());
        when(repository.save(any(Token.class))).thenReturn(token);
        service.generateToken(token.getEmail());
        assertNotNull(token);
    }

    @DisplayName("validarExpiracaoToken com usuário não encontrado")
    @Test
    void validarExpiracaoTokenUsuarioNaoEncontrado() {
        when(token.getEmail()).thenReturn("teste@teste.com");
        when(usuarioRepository.findByEmail(token.getEmail())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> service.validarExpiracaoToken(token.getEmail()));
    }

    @DisplayName("validarExpiracaoToken com token expirado")
    @Test
    void validarExpiracaoTokenExpirado() {
        Token token1 = new Token();
        token1.setExpiracao(OffsetDateTime.now().minusMinutes(60));
        when(token.getEmail()).thenReturn("teste@teste.com");
        when(usuarioRepository.findByEmail(token.getEmail())).thenReturn(Optional.of(usuario));
        when(usuario.getToken()).thenReturn(token1);
        assertThrows(UnauthorizedException.class, () -> service.validarExpiracaoToken(token.getEmail()));
    }

}
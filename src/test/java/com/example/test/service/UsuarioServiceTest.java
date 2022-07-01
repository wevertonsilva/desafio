package com.example.test.service;

import com.example.test.dto.UsuarioInDTO;
import com.example.test.dto.UsuarioLoginDTO;
import com.example.test.exception.BadRequestException;
import com.example.test.model.Usuario;
import com.example.test.repository.UsuarioRepository;
import lombok.Data;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.apache.commons.codec.digest.DigestUtils.sha256Hex;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

@Data
@ExtendWith(SpringExtension.class)
class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService service;

    @Mock
    private UsuarioRepository repository;

    @Mock
    private TokenService tokenService;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private UsuarioInDTO inDTO;

    @Mock
    private Usuario usuario;

    @Mock
    private UsuarioLoginDTO usuarioLoginDTO;

    @DisplayName("Persist ok")
    @Test
    void persistOk() {
        when(inDTO.getEmail()).thenReturn("teste@teste.com");
        when(repository.findByEmail(inDTO.getEmail())).thenReturn(Optional.empty());
        when(modelMapper.map(inDTO, Usuario.class)).thenReturn(usuario);
        when(inDTO.getSenha()).thenReturn("Alfabeto123&");
        service.persist(inDTO);
        verify(repository).save(any(Usuario.class));
    }

    @DisplayName("Persist com e-mail em uso")
    @Test
    void persistEmailEmUso() {
        when(inDTO.getEmail()).thenReturn("teste@teste.com");
        when(repository.findByEmail(inDTO.getEmail())).thenReturn(Optional.of(usuario));
        assertThrows(BadRequestException.class, () -> service.persist(inDTO));
    }

    @DisplayName("Persist com senha fraca")
    @Test
    void persistSenhaFraca() {
        when(inDTO.getEmail()).thenReturn("teste@teste.com");
        when(repository.findByEmail(inDTO.getEmail())).thenReturn(Optional.empty());
        when(modelMapper.map(inDTO, Usuario.class)).thenReturn(usuario);
        when(inDTO.getSenha()).thenReturn("123456");
        assertThrows(BadRequestException.class, () -> service.persist(inDTO));
    }

    @DisplayName("Login ok")
    @Test
    void loginOk() {
        when(usuarioLoginDTO.getEmail()).thenReturn("teste@teste.com");
        when(repository.findByEmail(usuarioLoginDTO.getEmail())).thenReturn(Optional.of(usuario));
        when(usuarioLoginDTO.getSenha()).thenReturn("Alfabeto123&");
        when(usuario.getSenha()).thenReturn(sha256Hex("Alfabeto123&"));
        service.login(usuarioLoginDTO);
        verify(repository).save(any(Usuario.class));
    }

    @DisplayName("Login com senha errada")
    @Test
    void loginSenhaErrada() {
        when(usuarioLoginDTO.getEmail()).thenReturn("teste@teste.com");
        when(repository.findByEmail(usuarioLoginDTO.getEmail())).thenReturn(Optional.empty());
        assertThrows(BadRequestException.class, () -> service.login(usuarioLoginDTO));
    }

}
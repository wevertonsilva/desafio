package com.example.test.service;

import com.example.test.dto.UsuarioInDTO;
import com.example.test.dto.UsuarioLoginDTO;
import com.example.test.dto.UsuarioOutDTO;
import com.example.test.exception.BadRequestException;
import com.example.test.model.Usuario;
import com.example.test.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.test.utils.Constants.*;
import static org.apache.commons.codec.digest.DigestUtils.sha256Hex;
import static org.springframework.http.HttpStatus.CREATED;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private TokenService tokenService;

    private ModelMapper modelMapper = new ModelMapper();

    public ResponseEntity<UsuarioOutDTO> persist (UsuarioInDTO dto) {
        validateUserExists(dto);
        Usuario map = modelMapper.map(dto, Usuario.class);
        map.setSenha(validaCriptografaSenha(dto.getSenha()));
        return ResponseEntity.status(CREATED).body(modelMapper.map(repository.save(map), UsuarioOutDTO.class));
    }

    private void validateUserExists(UsuarioInDTO dto) {
        Optional<Usuario> byEmail = repository.findByEmail(dto.getEmail());
        if (byEmail.isPresent())
            throw new BadRequestException(USUARIO_JA_EXISTENTE);
    }

    private String validaCriptografaSenha(String senha) {
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=.*!])"
                + "(?=\\S+$).{8,20}$";

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(senha);
        boolean matches = m.matches();

        if(!matches)
            throw new BadRequestException(SENHA_FRACA);

        return sha256Hex(senha);
    }

    public ResponseEntity<UsuarioOutDTO> login(UsuarioLoginDTO dto) {
        Optional<Usuario> usuario = repository.findByEmail(dto.getEmail());
        if (usuario.isEmpty() || !usuario.get().getSenha().equals(sha256Hex(dto.getSenha())))
            throw new BadRequestException(EMAIL_SENHA_INCORRETOS);

        usuario.get().setToken(tokenService.generateToken(dto.getEmail()));
        repository.save(usuario.get());
        return ResponseEntity.ok(modelMapper.map(usuario.get(), UsuarioOutDTO.class));
    }

}

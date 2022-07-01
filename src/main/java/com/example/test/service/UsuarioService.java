package com.example.test.service;

import com.example.test.dto.UsuarioInDTO;
import com.example.test.dto.UsuarioOutDTO;
import com.example.test.model.Usuario;
import com.example.test.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.commons.codec.digest.DigestUtils.sha256Hex;
import static org.springframework.http.HttpStatus.CREATED;

@Service
public class UsuarioService {

    public static final String USUARIO_JA_EXISTENTE = "Usuário já existente";

    @Autowired
    private UsuarioRepository repository;

    private ModelMapper modelMapper = new ModelMapper();;

    public ResponseEntity<UsuarioOutDTO> persist (UsuarioInDTO dto) throws Exception {
        validateUserExists(dto);
        Usuario map = modelMapper.map(dto, Usuario.class);
        map.setSenha(validaCriptografaSenha(dto.getSenha()));
        return ResponseEntity.status(CREATED).body(modelMapper.map(repository.save(map), UsuarioOutDTO.class));
    }

    private void validateUserExists(UsuarioInDTO dto) throws Exception {
        Optional<Usuario> byEmail = repository.findByEmail(dto.getEmail());
        if (byEmail.isPresent())
            //throw exception
            throw new Exception(USUARIO_JA_EXISTENTE);
    }

    private String validaCriptografaSenha(String senha) throws Exception {
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(senha);
        boolean matches = m.matches();

        if(!matches)
            //throw Exeception
            throw new Exception("Senha fraca");

        return sha256Hex(senha);
    }
}

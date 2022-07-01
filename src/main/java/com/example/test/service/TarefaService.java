package com.example.test.service;

import com.example.test.dto.TarefaInDTO;
import com.example.test.dto.TarefaOutDTO;
import com.example.test.dto.TarefaUpdateDTO;
import com.example.test.dto.UsuarioInfosDTO;
import com.example.test.enun.StatusEnum;
import com.example.test.model.Tarefa;
import com.example.test.model.Usuario;
import com.example.test.repository.TarefaRepository;
import com.example.test.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

import static com.example.test.utils.Constants.*;
import static java.util.Objects.*;
import static org.apache.commons.codec.digest.DigestUtils.sha256Hex;
import static org.springframework.http.HttpStatus.CREATED;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final ModelMapper modelMapper = new ModelMapper();;

    public ResponseEntity<TarefaOutDTO> persist (TarefaInDTO dto, UsuarioInfosDTO usuarioInfos) throws Exception {
        Optional<Usuario> byId = usuarioRepository.findById(usuarioInfos.getId());
        Tarefa map = modelMapper.map(dto, Tarefa.class);
        if(byId.isPresent())
            map.setUsuario(byId.get());
        map.setStatus(StatusEnum.PENDENTE);
        return ResponseEntity.status(CREATED).body(modelMapper.map(repository.save(map), TarefaOutDTO.class));
    }

    public ResponseEntity<TarefaOutDTO> update(TarefaUpdateDTO dto, UsuarioInfosDTO usuarioInfos) throws Exception {
        Optional<Tarefa> tarefaOptional = repository.findById(dto.getId());

        if(tarefaOptional.isEmpty())
            throw new Exception(TAREFA_NAO_ENCONTRADA);

        Tarefa tarefa = tarefaOptional.get();
        validateTarefa(tarefa, usuarioInfos);

        tarefa.setDescricao(nonNull(dto.getDescricao()) ? dto.getDescricao() : tarefa.getDescricao());
        tarefa.setPrioridade(nonNull(dto.getPrioridade()) ? dto.getPrioridade() : tarefa.getPrioridade());

        return ResponseEntity.ok(modelMapper.map(repository.save(tarefa), TarefaOutDTO.class));
    }

    public ResponseEntity<Object> delete (Long id, UsuarioInfosDTO usuarioInfos) throws Exception {
        Optional<Tarefa> tarefa = repository.findById(id);

        if(tarefa.isEmpty())
            throw new Exception(TAREFA_NAO_ENCONTRADA);

        validateTarefa(tarefa.get(), usuarioInfos);
        repository.delete(tarefa.get());
        return ResponseEntity.ok(TAREFA_REMOVIDA_SUCESSO);
    }

    private void validateTarefa(Tarefa tarefa, UsuarioInfosDTO usuarioInfos) throws Exception {
        if(!Objects.equals(tarefa.getUsuario().getId(), usuarioInfos.getId()))
            throw new Exception(TAREFA_NAO_PERTENCE_USUARIO);
    }
}

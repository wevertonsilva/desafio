package com.example.test.service;

import com.example.test.dto.TarefaInDTO;
import com.example.test.dto.TarefaOutDTO;
import com.example.test.dto.TarefaUpdateDTO;
import com.example.test.dto.UsuarioInfosDTO;
import com.example.test.enun.StatusEnum;
import com.example.test.exception.BadRequestException;
import com.example.test.exception.NotFoundException;
import com.example.test.repository.model.Tarefa;
import com.example.test.repository.model.Usuario;
import com.example.test.repository.TarefaRepository;
import com.example.test.repository.UsuarioRepository;
import com.example.test.utils.ParameterFind;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

import static com.example.test.utils.Constants.*;
import static java.util.Objects.nonNull;
import static org.springframework.http.HttpStatus.CREATED;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public static final Integer PAGE = 0;

    public static final Integer SIZE = 10;

    private final ModelMapper modelMapper = new ModelMapper();

    public ResponseEntity<TarefaOutDTO> persist (TarefaInDTO dto, UsuarioInfosDTO usuarioInfos) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(usuarioInfos.getEmail());
        Tarefa tarefa = modelMapper.map(dto, Tarefa.class);
        usuario.ifPresent(tarefa::setUsuario);
        tarefa.setStatus(StatusEnum.PENDENTE);
        return ResponseEntity.status(CREATED).body(modelMapper.map(repository.save(tarefa), TarefaOutDTO.class));
    }

    public ResponseEntity<Page<TarefaOutDTO>> findAll(UsuarioInfosDTO usuarioInfos, ParameterFind parameterFind) {
        Page<Tarefa> tarefas = getAllTarefaByUser(usuarioInfos, parameterFind);
        Page<TarefaOutDTO> tarefaFinalList = tarefas.map(tarefa -> modelMapper.map(tarefa, TarefaOutDTO.class));
        return ResponseEntity.ok(tarefaFinalList);
    }

    public ResponseEntity<TarefaOutDTO> update(TarefaUpdateDTO dto, UsuarioInfosDTO usuarioInfos) {
        Tarefa tarefa = getTarefa(dto.getId(), usuarioInfos);
        tarefa.setDescricao(nonNull(dto.getDescricao()) ? dto.getDescricao() : tarefa.getDescricao());
        tarefa.setPrioridade(nonNull(dto.getPrioridade()) ? dto.getPrioridade() : tarefa.getPrioridade());
        return ResponseEntity.ok(modelMapper.map(repository.save(tarefa), TarefaOutDTO.class));
    }

    public ResponseEntity<TarefaOutDTO> concluirTarefa(Long id, UsuarioInfosDTO usuarioInfos) {
        Tarefa tarefa = getTarefa(id, usuarioInfos);
        tarefa.setStatus(StatusEnum.CONCLUIDA);
        return ResponseEntity.ok(modelMapper.map(repository.save(tarefa), TarefaOutDTO.class));
    }

    public ResponseEntity<Object> delete (Long id, UsuarioInfosDTO usuarioInfos) {
        Tarefa tarefa = getTarefa(id, usuarioInfos);
        repository.delete(tarefa);
        return ResponseEntity.ok(TAREFA_REMOVIDA_SUCESSO);
    }

    private Tarefa getTarefa(Long id, UsuarioInfosDTO usuarioInfos) {
        Optional<Tarefa> tarefa = repository.findById(id);

        if(tarefa.isEmpty())
            throw new NotFoundException(TAREFA_NAO_ENCONTRADA);

        validateTarefa(tarefa.get(), usuarioInfos);
        return tarefa.get();
    }

    private void validateTarefa(Tarefa tarefa, UsuarioInfosDTO usuarioInfos) {
        if(!Objects.equals(tarefa.getUsuario().getEmail(), usuarioInfos.getEmail()))
            throw new BadRequestException(TAREFA_NAO_PERTENCE_USUARIO);
    }

    public Page<Tarefa> getAllTarefaByUser(UsuarioInfosDTO usuarioInfos, ParameterFind parameterFind) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(usuarioInfos.getEmail());
        if (usuario.isEmpty())
            throw new NotFoundException(USUARIO_NAO_ENCONTRADO);

        parameterFind.setPage(Objects.isNull(parameterFind.getPage()) ? PAGE : parameterFind.getPage());
        parameterFind.setSize(Objects.isNull(parameterFind.getSize()) ? SIZE : parameterFind.getSize());

        Pageable pageRequest = PageRequest.of(parameterFind.getPage(), parameterFind.getSize(), Sort.by("id").ascending());
        if (nonNull(parameterFind.getPrioridade()))
            return repository.findByPrioridadeAndUsuarioIdAndStatus(parameterFind.getPrioridade(), usuario.get().getId(), StatusEnum.PENDENTE, pageRequest);

        return repository.findByUsuarioIdAndStatus(usuario.get().getId(), StatusEnum.PENDENTE, pageRequest);
    }


}

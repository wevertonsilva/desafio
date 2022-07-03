package com.example.test.service;

import com.example.test.dto.TarefaInDTO;
import com.example.test.dto.TarefaOutDTO;
import com.example.test.dto.TarefaUpdateDTO;
import com.example.test.dto.UsuarioInfosDTO;
import com.example.test.enun.PrioridadeEnum;
import com.example.test.exception.BadRequestException;
import com.example.test.exception.NotFoundException;
import com.example.test.repository.model.Tarefa;
import com.example.test.repository.model.Usuario;
import com.example.test.repository.TarefaRepository;
import com.example.test.repository.UsuarioRepository;
import com.example.test.utils.ParameterFind;
import io.jsonwebtoken.lang.Assert;
import lombok.Data;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@Data
@ExtendWith(SpringExtension.class)
class TarefaServiceTest {

    @InjectMocks
    private TarefaService service;

    @Mock
    private TarefaRepository repository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private Usuario usuario;

    @Mock
    private Tarefa tarefa;

    @Mock
    private UsuarioInfosDTO usuarioInfosDTO;

    @Mock
    private TarefaInDTO inDTO;

    @Mock
    private TarefaOutDTO outDTO;

    @Mock
    private Page<TarefaOutDTO> pageOutDto;

    @Mock
    private ParameterFind parameterFind;

    @Mock
    private TarefaUpdateDTO tarefaUpdateDTO;

    @DisplayName("Persist ok")
    @Test
    void persistOk() {
        when(usuarioInfosDTO.getEmail()).thenReturn("teste@teste.com");
        when(usuarioRepository.findByEmail(usuarioInfosDTO.getEmail())).thenReturn(Optional.of(usuario));
        when(modelMapper.map(inDTO, Tarefa.class)).thenReturn(tarefa);
        when(modelMapper.map(tarefa, TarefaOutDTO.class)).thenReturn(outDTO);
        when(repository.save(any(Tarefa.class))).thenReturn(tarefa);
        service.persist(inDTO, usuarioInfosDTO);
        verify(repository).save(any(Tarefa.class));
    }

    @DisplayName("FindAll ok")
    @Test
    void findAllOk() {
        Page<Tarefa> newPage = new PageImpl<>(List.of(tarefa));
        ParameterFind paramNew = new ParameterFind();
        paramNew.setPage(null);
        paramNew.setSize(null);
        when(usuarioRepository.findByEmail(usuarioInfosDTO.getEmail())).thenReturn(Optional.of(usuario));
        when(service.getAllTarefaByUser(usuarioInfosDTO, paramNew)).thenReturn(newPage);
        service.findAll(usuarioInfosDTO, paramNew);
        assertNotNull(pageOutDto);
    }

    @DisplayName("FindAll ok com prioridade")
    @Test
    void findAllOkPrioridade() {
        Page<Tarefa> newPage = new PageImpl<>(List.of(tarefa));
        ParameterFind paramNew = new ParameterFind();
        paramNew.setPage(null);
        paramNew.setSize(null);
        paramNew.setPrioridade(PrioridadeEnum.ALTA);
        when(usuarioRepository.findByEmail(usuarioInfosDTO.getEmail())).thenReturn(Optional.of(usuario));
        when(service.getAllTarefaByUser(usuarioInfosDTO, paramNew)).thenReturn(newPage);
        service.findAll(usuarioInfosDTO, paramNew);
        assertNotNull(pageOutDto);
    }

    @DisplayName("FindAll Usuário não encontrado")
    @Test
    void findAllUsuarioNaoEncontrado() {
        assertThrows(NotFoundException.class, () -> service.getAllTarefaByUser(usuarioInfosDTO, parameterFind));
    }

    @DisplayName("Update ok")
    @Test
    void updateOk() {
        Usuario usuario1 = new Usuario();
        usuario1.setEmail("teste@teste.com");
        when(usuarioInfosDTO.getEmail()).thenReturn("teste@teste.com");
        when(tarefa.getUsuario()).thenReturn(usuario1);
        when(repository.findById(anyLong())).thenReturn(Optional.of(tarefa));
        when(repository.save(any(Tarefa.class))).thenReturn(tarefa);
        service.update(tarefaUpdateDTO, usuarioInfosDTO);
        verify(repository).save(any(Tarefa.class));
    }

    @DisplayName("Update com tarefa não encontrada")
    @Test
    void updateTarefaNaoEncontrada() {
        Usuario usuario1 = new Usuario();
        usuario1.setEmail("teste@teste.com");
        when(usuarioInfosDTO.getEmail()).thenReturn("teste@teste.com");
        when(tarefa.getUsuario()).thenReturn(usuario1);
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> service.update(tarefaUpdateDTO, usuarioInfosDTO));
    }

    @DisplayName("Update com tarefa não pertencente ao usuário")
    @Test
    void updateTarefaNaoPertencenteUsuario() {
        Usuario usuario1 = new Usuario();
        usuario1.setEmail("nada@teste.com");
        when(usuarioInfosDTO.getEmail()).thenReturn("teste@teste.com");
        when(tarefa.getUsuario()).thenReturn(usuario1);
        when(repository.findById(anyLong())).thenReturn(Optional.of(tarefa));
        when(repository.save(any(Tarefa.class))).thenReturn(tarefa);
        assertThrows(BadRequestException.class, () -> service.update(tarefaUpdateDTO, usuarioInfosDTO));
    }

    @DisplayName("concluirTarefa ok")
    @Test
    void concluirTarefaOk() {
        Usuario usuario1 = new Usuario();
        usuario1.setEmail("teste@teste.com");
        when(usuarioInfosDTO.getEmail()).thenReturn("teste@teste.com");
        when(tarefa.getUsuario()).thenReturn(usuario1);
        when(repository.findById(anyLong())).thenReturn(Optional.of(tarefa));
        when(repository.save(any(Tarefa.class))).thenReturn(tarefa);
        service.concluirTarefa(anyLong(), usuarioInfosDTO);
        Assert.notNull(tarefa);
    }

    @DisplayName("delete Ok")
    @Test
    void deleteOk() {
        Usuario usuario1 = new Usuario();
        usuario1.setEmail("teste@teste.com");
        when(usuarioInfosDTO.getEmail()).thenReturn("teste@teste.com");
        when(tarefa.getUsuario()).thenReturn(usuario1);
        when(repository.findById(anyLong())).thenReturn(Optional.of(tarefa));
        when(repository.save(any(Tarefa.class))).thenReturn(tarefa);
        service.delete(anyLong(), usuarioInfosDTO);
        verify(repository).delete(any(Tarefa.class));
    }

}
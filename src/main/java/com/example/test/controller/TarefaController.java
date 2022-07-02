package com.example.test.controller;

import com.example.test.dto.TarefaInDTO;
import com.example.test.dto.TarefaOutDTO;
import com.example.test.dto.TarefaUpdateDTO;
import com.example.test.dto.UsuarioInfosDTO;
import com.example.test.enun.PrioridadeEnum;
import com.example.test.service.TarefaService;
import com.example.test.utils.ParameterFind;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/tarefa")
public class TarefaController extends BaseController{

    @Autowired
    private TarefaService service;

    @GetMapping("/usuario")
    @ApiOperation(nickname = "findAll", value = "Busca todas as tarefas do usuário logado", response = ResponseEntity.class, httpMethod = "GET", authorizations = {@Authorization(value = "Bearer")})
    public ResponseEntity<Page<TarefaOutDTO>> findAll(@RequestParam(value = "page", required = false) Integer page,
                                                      @RequestParam(value = "size", required = false) Integer size,
                                                      @RequestParam(value = "prioridade", required = false) PrioridadeEnum prioridade,
                                                      HttpServletRequest request) {
        UsuarioInfosDTO usuarioInfos = getUsuarioInfosByToken(request);
        ParameterFind parameterFind = ParameterFind.builder().page(page).size(size).prioridade(prioridade).build();
        return service.findAll(usuarioInfos, parameterFind);
    }

    @PostMapping
    @ApiOperation(nickname = "persist", value = "Salva uma nova tarefa para o usuário logado", response = ResponseEntity.class, httpMethod = "POST", authorizations = {@Authorization(value = "Bearer")})
    public ResponseEntity<TarefaOutDTO> persist (@RequestBody @Valid TarefaInDTO dto, HttpServletRequest request) {
        UsuarioInfosDTO usuarioInfos = getUsuarioInfosByToken(request);
        return service.persist(dto, usuarioInfos);
    }

    @PutMapping
    @ApiOperation(nickname = "update", value = "Atualiza uma tarefa do usuário logado", response = ResponseEntity.class, httpMethod = "PUT", authorizations = {@Authorization(value = "Bearer")})
    public ResponseEntity<TarefaOutDTO> update (@RequestBody @Valid TarefaUpdateDTO dto, HttpServletRequest request) {
        UsuarioInfosDTO usuarioInfos = getUsuarioInfosByToken(request);
        return service.update(dto, usuarioInfos);
    }

    @PutMapping("/concluir-tarefa/{id}")
    @ApiOperation(nickname = "concluir", value = "Concluir uma tarefa do usuário logado", response = ResponseEntity.class, httpMethod = "PUT", authorizations = {@Authorization(value = "Bearer")})
    public ResponseEntity<TarefaOutDTO> concluirTarefa (@PathVariable Long id, HttpServletRequest request) {
        UsuarioInfosDTO usuarioInfos = getUsuarioInfosByToken(request);
        return service.concluirTarefa(id, usuarioInfos);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(nickname = "delete", value = "Remove uma tarefa do usuário logado", response = ResponseEntity.class, httpMethod = "DELETE", authorizations = {@Authorization(value = "Bearer")})
    public ResponseEntity<Object> delete (@PathVariable Long id, HttpServletRequest request) {
        UsuarioInfosDTO usuarioInfos = getUsuarioInfosByToken(request);
        return service.delete(id, usuarioInfos);
    }

}

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

@Api(value = "ALOOOO",description = "ALOOOOO")
@RestController
@RequestMapping("/tarefa")
public class TarefaController extends BaseController{

    @Autowired
    private TarefaService service;

    @GetMapping
    public ResponseEntity<Page<TarefaOutDTO>> findAll(@RequestParam(value = "page") Integer page,
                                                      @RequestParam(value = "size") Integer size,
                                                      @RequestParam(value = "prioridade", required = false) PrioridadeEnum prioridade) {
        ParameterFind parameterFind = ParameterFind.builder().page(page).size(size).prioridade(prioridade).build();
        return service.findAll(parameterFind);
    }

    @PostMapping
    @ApiOperation(nickname = "findAll", value = "get all data", response = String.class, httpMethod = "GET", authorizations = {@Authorization(value = "Bearer")})
    public ResponseEntity<TarefaOutDTO> persist (@RequestBody @Valid TarefaInDTO dto, HttpServletRequest request) {
        UsuarioInfosDTO usuarioInfos = getUsuarioInfosByToken(request);
        return service.persist(dto, usuarioInfos);
    }

    @PutMapping
    @ApiOperation(nickname = "update", value = "update data", response = String.class, httpMethod = "PUT", authorizations = {@Authorization(value = "Bearer")})
    public ResponseEntity<TarefaOutDTO> update (@RequestBody @Valid TarefaUpdateDTO dto, HttpServletRequest request) {
        UsuarioInfosDTO usuarioInfos = getUsuarioInfosByToken(request);
        return service.update(dto, usuarioInfos);
    }

    @PutMapping("/concluir-tarefa/{id}")
    @ApiOperation(nickname = "concluir", value = "concluir tarefa", response = String.class, httpMethod = "PUT", authorizations = {@Authorization(value = "Bearer")})
    public ResponseEntity<TarefaOutDTO> concluirTarefa (@PathVariable Long id, HttpServletRequest request) {
        UsuarioInfosDTO usuarioInfos = getUsuarioInfosByToken(request);
        return service.concluirTarefa(id, usuarioInfos);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(nickname = "delete", value = "delete data", response = String.class, httpMethod = "DELETE", authorizations = {@Authorization(value = "Bearer")})
    public ResponseEntity<Object> delete (@PathVariable Long id, HttpServletRequest request) {
        UsuarioInfosDTO usuarioInfos = getUsuarioInfosByToken(request);
        return service.delete(id, usuarioInfos);
    }

}

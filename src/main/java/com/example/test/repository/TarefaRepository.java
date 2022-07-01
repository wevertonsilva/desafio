package com.example.test.repository;

import com.example.test.enun.PrioridadeEnum;
import com.example.test.model.Tarefa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

   Optional<Tarefa> findByDescricao(String desc);

   Page<Tarefa> findByPrioridade(PrioridadeEnum prioridade, Pageable pageRequest);

}

package com.example.test.repository;

import com.example.test.enun.PrioridadeEnum;
import com.example.test.enun.StatusEnum;
import com.example.test.repository.model.Tarefa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {


   Page<Tarefa> findByUsuarioIdAndStatus(Long id, StatusEnum status, Pageable pageRequest);

   Page<Tarefa> findByPrioridadeAndUsuarioIdAndStatus(PrioridadeEnum prioridade, Long id, StatusEnum status, Pageable pageRequest);

}

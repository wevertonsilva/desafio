package com.example.test.repository;

import com.example.test.model.Tarefa;
import com.example.test.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

   Optional<Tarefa> findByDescricao(String desc);

}

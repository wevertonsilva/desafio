package com.example.test.model;

import com.example.test.enun.PrioridadeEnum;
import com.example.test.enun.StatusEnum;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "tarefa")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String descricao;

    @NotBlank
    @Enumerated(EnumType.STRING)
    private PrioridadeEnum prioridade;

    @NotBlank
    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @ManyToOne
    @JoinColumn(name="usuario_id", nullable=false)
    private Usuario usuario;
}

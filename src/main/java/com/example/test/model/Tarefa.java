package com.example.test.model;

import com.example.test.enun.PrioridadeEnum;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tarefa")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    private PrioridadeEnum prioriodade;
}

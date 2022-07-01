package com.example.test.dto;

import com.example.test.enun.PrioridadeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TarefaDTO {

    //Used only update
    private Long id;

    private String descricao;

    private PrioridadeEnum prioriodade;
}

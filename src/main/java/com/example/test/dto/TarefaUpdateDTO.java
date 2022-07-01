package com.example.test.dto;

import com.example.test.enun.PrioridadeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TarefaUpdateDTO {

    @NotNull
    private Long id;

    private String descricao;

    private PrioridadeEnum prioridade;

}

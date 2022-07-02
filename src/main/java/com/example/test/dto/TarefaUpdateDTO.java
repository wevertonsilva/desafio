package com.example.test.dto;

import com.example.test.enun.PrioridadeEnum;
import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(notes = "Id da tarefa", example = "1", required = true)
    private Long id;

    @ApiModelProperty(notes = "Descrição da tarefa", example = "Fazer café", required = true)
    private String descricao;

    @ApiModelProperty(notes = "Prioridade da tarefa", example = "ALTA", required = true)
    private PrioridadeEnum prioridade;

}

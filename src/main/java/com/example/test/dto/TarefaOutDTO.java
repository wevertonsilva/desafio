package com.example.test.dto;

import com.example.test.enun.PrioridadeEnum;
import com.example.test.enun.StatusEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TarefaOutDTO {

    @ApiModelProperty(notes = "Id da tarefa", example = "1")
    private Long id;

    @ApiModelProperty(notes = "Descrição da tarefa", example = "Fazer café", required = true)
    private String descricao;

    @ApiModelProperty(notes = "Prioridade da tarefa", example = "ALTA", required = true)
    private PrioridadeEnum prioridade;

    @ApiModelProperty(notes = "Status da tarefa", example = "PENDENTE")
    private StatusEnum status;
}

package com.example.test.dto;

import com.example.test.enun.PrioridadeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TarefaInDTO {

    @NotBlank
    @ApiModelProperty(notes = "Descrição da tarefa", example = "Fazer café", required = true)
    private String descricao;

    @NotNull
    @ApiModelProperty(notes = "Prioridade da tarefa", example = "ALTA", required = true)
    private PrioridadeEnum prioridade;
}

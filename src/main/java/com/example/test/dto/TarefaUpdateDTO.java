package com.example.test.dto;

import com.example.test.enun.PrioridadeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.example.test.utils.Constants.CAMPO_OBRIGATORIO;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TarefaUpdateDTO {

    @NotBlank(message = CAMPO_OBRIGATORIO)
    @ApiModelProperty(notes = "Id da tarefa", example = "1", required = true)
    private Long id;

    @ApiModelProperty(notes = "Descrição da tarefa", example = "Fazer café", required = true)
    private String descricao;

    @ApiModelProperty(notes = "Prioridade da tarefa", example = "ALTA", required = true)
    private PrioridadeEnum prioridade;

}

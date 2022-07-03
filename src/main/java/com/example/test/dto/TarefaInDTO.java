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
public class TarefaInDTO {

    @NotBlank(message = CAMPO_OBRIGATORIO)
    @ApiModelProperty(notes = "Descrição da tarefa", example = "Fazer café", required = true)
    private String descricao;

    @NotNull(message = CAMPO_OBRIGATORIO)
    @ApiModelProperty(notes = "Prioridade da tarefa", example = "ALTA", required = true)
    private PrioridadeEnum prioridade;
}

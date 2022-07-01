package com.example.test.dto;

import com.example.test.enun.PrioridadeEnum;
import com.example.test.enun.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TarefaOutDTO {

    private Long id;

    private String descricao;

    private PrioridadeEnum prioridade;

    private StatusEnum status;
}

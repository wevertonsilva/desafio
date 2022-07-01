package com.example.test.utils;

import com.example.test.enun.PrioridadeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParameterFind {

    private Integer page = 0;
    private Integer size = 10;
    private PrioridadeEnum prioridade;

}

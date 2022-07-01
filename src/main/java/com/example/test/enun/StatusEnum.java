package com.example.test.enun;

public enum StatusEnum {

    CONCLUIDA("Concluida"),
    PENDENTE("Pendente");

    String value;

    StatusEnum(String value) {
        this.value =value;
    }
}

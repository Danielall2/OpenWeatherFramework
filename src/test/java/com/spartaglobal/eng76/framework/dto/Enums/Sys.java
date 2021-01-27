package com.spartaglobal.eng76.framework.dto.Enums;

public enum Sys {
    TYPE("type"),
    ID("id"),
    COUNTRY("country"),
    SUNRISE("sunrise"),
    SUNSET("sunset");

    private String field;

    Sys(String field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return field;
    }
}

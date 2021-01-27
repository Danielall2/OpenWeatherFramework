package com.spartaglobal.eng76.framework.dto.Enums;

public enum Weather {
    ID("id"),
    MAIN("main"),
    DESCRIPTION("description"),
    ICON("icon");

    private String field;

    Weather(String field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return field;
    }
}

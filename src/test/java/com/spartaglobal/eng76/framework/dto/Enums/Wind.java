package com.spartaglobal.eng76.framework.dto.Enums;

public enum Wind {
    SPEED("speed"),
    DEGREES("deg"),
    GUST("gust");

    private String field;

    Wind(String field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return field;
    }
}

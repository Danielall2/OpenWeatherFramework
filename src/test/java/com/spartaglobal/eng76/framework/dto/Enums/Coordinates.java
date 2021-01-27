package com.spartaglobal.eng76.framework.dto.Enums;

public enum Coordinates {
    LONGITUDE("lon"),
    LATITUDE("lat");

    private String field;

    Coordinates(String field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return field;
    }
}

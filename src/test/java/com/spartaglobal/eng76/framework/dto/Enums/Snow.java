package com.spartaglobal.eng76.framework.dto.Enums;

public enum Snow {
    SNOW_ONE_HOUR("1h"),
    SNOW_THREE_HOURS("3h");

    private String field;

    Snow(String field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return field;
    }
}

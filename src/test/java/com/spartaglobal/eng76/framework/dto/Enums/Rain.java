package com.spartaglobal.eng76.framework.dto.Enums;

public enum Rain {
    RAIN_ONE_HOUR("1h"),
    RAIN_THREE_HOURS("3h");

    private String field;

    Rain(String field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return field;
    }

}

package com.spartaglobal.eng76.framework.dto.Enums;

public enum Clouds {
    ALL("all");

    private String field;

    Clouds(String field) {
        this.field=field;
    }

    @Override
    public String toString() {
        return field;
    }
}

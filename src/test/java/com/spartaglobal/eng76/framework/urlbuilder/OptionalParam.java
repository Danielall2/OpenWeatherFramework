package com.spartaglobal.eng76.framework.urlbuilder;

import java.util.Map;

/**
 * @author Samurah
 * @version 1.0
 * @since 1.1
 */

public enum OptionalParam {
    MODE("mode"),
    UNITS("units"),
    COUNT("cnt"),
    LANG("lang");

    private final String field;

    OptionalParam(String field){
        this.field = field;
    }

    public Map.Entry<String, String> ofValue(String value){
        return Map.entry(this.toString(), value);
    }

    @Override
    public String toString() {
        return field;
    }
}

package com.spartaglobal.eng76.framework.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "cod",
        "calctime",
        "count",
        "list",
})

public class WeatherListDTO {

    @JsonProperty("coord")
    private String coord;

    @JsonProperty("calctime")
    private String calctime;

    @JsonProperty("cnt")
    private String count;

    @JsonProperty("list")
    private List<WeatherDTO> list;

    public WeatherListDTO() {

    }

    public String getCoord() {
        return coord;
    }

    public String getCalctime() {
        return calctime;
    }

    public String getCount() {
        return count;
    }

    public List<WeatherDTO> getList() {
        return list;
    }


}

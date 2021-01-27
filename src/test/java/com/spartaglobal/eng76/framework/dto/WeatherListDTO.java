package com.spartaglobal.eng76.framework.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.util.JSONPObject;

import java.util.ArrayList;
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

    @JsonProperty("count")
    private String count;

    @JsonProperty("list")
    private List<JSONPObject> list;

    private ArrayList<WeatherDTO> weatherDTOList;

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

    public List<JSONPObject> getList() {
        return list;
    }

    // Need a method to convert inject the list of JSON objects into a list of WeatherDTOs



}

package com.spartaglobal.eng76.framework.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "coord",
        "weather",
        "base",
        "main",
        "wind",
        "clouds",
        "rain",
        "snow",
        "dt",
        "sys",
        "timezone",
        "id",
        "name",
        "cod"
})

public class WeatherDTO {

    @JsonProperty("coord")
    private String coord;

    @JsonProperty("weather")
    private String weather;

    @JsonProperty("base")
    private String base;

    @JsonProperty("main")
    private String main;

    @JsonProperty("wind")
    private String wind;

    @JsonProperty("clouds")
    private String clouds;

    @JsonProperty("rain")
    private String rain;

    @JsonProperty("snow")
    private String snow;

    @JsonProperty("dt")
    private String dt;

    @JsonProperty("sys")
    private String sys;

    @JsonProperty("timezone")
    private String timezone;

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("cod")
    private String cod;


}

package com.spartaglobal.eng76.framework.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.spartaglobal.eng76.framework.connectionmanager.ConnectionManager;

import java.net.http.HttpResponse;
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
    private List<WeatherDTO> list;

    private ArrayList<WeatherDTO> weatherDTOList;

    private ConnectionManager connectionManager;

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

    public ArrayList<WeatherDTO> getWeatherDTOList() {
        return weatherDTOList;
    }

    public ConnectionManager getConnectionManager() {
        return connectionManager;
    }

    public void setConnectionManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    // Need a method to convert inject the list of JSON objects into a list of WeatherDTOs


}


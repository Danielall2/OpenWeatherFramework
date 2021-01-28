package com.spartaglobal.eng76.framework.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.spartaglobal.eng76.framework.connectionmanager.ConnectionManager;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "message",
        "cod",
        "count",
        "calctime",
        "cnt",
        "list",
})

public class WeatherListDTO {

    @JsonProperty("message")
    private String message;

    @JsonProperty("cod")
    private String cod;

    @JsonProperty("count")
    private String count;

    @JsonProperty("calctime")
    private String calctime;

    @JsonProperty("cnt")
    private String cnt;

    @JsonProperty("list")
    private List<WeatherDTO> list;

    @JsonIgnore
    private ConnectionManager connectionManager;

    public WeatherListDTO() {

    }

    public String getCod() {
        return cod;
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

    public ConnectionManager getConnectionManager() {
        return connectionManager;
    }

    public void setConnectionManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public String getMessage() {
        return message;
    }

    public String getCnt() {
        return cnt;
    }
}


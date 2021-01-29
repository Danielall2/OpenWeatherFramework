package com.spartaglobal.eng76.framework.injector;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spartaglobal.eng76.framework.connectionmanager.ConnectionManager;
import com.spartaglobal.eng76.framework.dto.WeatherDTO;
import com.spartaglobal.eng76.framework.dto.WeatherListDTO;
import com.spartaglobal.eng76.framework.exceptions.FailedHttpConnectionException;

import java.net.http.HttpResponse;

public class Injector {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static WeatherDTO injectIntoWeatherDTO(ConnectionManager connectionManager) throws FailedHttpConnectionException {
        if(connectionManager == null){
            throw new IllegalArgumentException("Connection manager should not be null!");
        }
        try {
            WeatherDTO weatherDTO = objectMapper.readValue(connectionManager.getHttpResponse().body(), WeatherDTO.class);
            weatherDTO.setConnectionManager(connectionManager);
            return weatherDTO;
        } catch (JsonProcessingException e) {
            throw new FailedHttpConnectionException(connectionManager);
        }
    }

    public static WeatherListDTO injectIntoWeatherListDTO(ConnectionManager connectionManager) throws FailedHttpConnectionException {
        if(connectionManager == null){
            throw new IllegalArgumentException("Connection manager should not be null!");
        }
        try {
            WeatherListDTO weatherListDTO = objectMapper.readValue(connectionManager.getHttpResponse().body(), WeatherListDTO.class);
            weatherListDTO.setConnectionManager(connectionManager);
            return weatherListDTO;
        } catch (JsonProcessingException e) {
            throw new FailedHttpConnectionException(connectionManager);
        }
    }

//    public ParentDTO injectIntoGenericDTO(String url) {
//        connectionManager.connectToAPI(url);
//        ParentDTO dtoChild = Factory.getDTO(url);
//        return objectMapper.readValue(connectionManager.getHttpResponseBody(), dtoChild.class);
//    }

}


package com.spartaglobal.eng76.framework.injector;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spartaglobal.eng76.framework.connectionmanager.ConnectionManager;
import com.spartaglobal.eng76.framework.dto.ParentDTO;
import com.spartaglobal.eng76.framework.dto.WeatherDTO;
import com.spartaglobal.eng76.framework.dto.WeatherListDTO;

public class Injector {

    private ObjectMapper objectMapper = new ObjectMapper();
    private ConnectionManager connectionManager = new ConnectionManager();

    public WeatherDTO injectIntoWeatherDTO(String url) {
//        WeatherDTO weatherDTO = Factory.getDTO(url);
        connectionManager.connectToAPI(url);
        try {
            return objectMapper.readValue(connectionManager.getHttpResponseBody(), WeatherDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public WeatherListDTO injectIntoWeatherListDTO(String url) {
        connectionManager.connectToAPI(url);
        try {
            return objectMapper.readValue(connectionManager.getHttpResponseBody(), WeatherListDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public ParentDTO injectIntoGenericDTO(String url) {
//        connectionManager.connectToAPI(url);
//        ParentDTO dtoChild = Factory.getDTO(url);
//        return objectMapper.readValue(connectionManager.getHttpResponseBody(), dtoChild.class);
//    }

}

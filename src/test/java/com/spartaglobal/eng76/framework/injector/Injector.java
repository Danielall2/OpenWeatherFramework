package com.spartaglobal.eng76.framework.injector;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spartaglobal.eng76.framework.connectionmanager.ConnectionManager;
import com.spartaglobal.eng76.framework.dto.ParentDTO;
import com.spartaglobal.eng76.framework.dto.WeatherDTO;
import com.spartaglobal.eng76.framework.dto.WeatherListDTO;

import java.net.http.HttpResponse;

public class Injector {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static WeatherDTO injectIntoWeatherDTO(String response) {
        try {
            return objectMapper.readValue(response, WeatherDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static WeatherListDTO injectIntoWeatherListDTO(String response) {
        try {
            return objectMapper.readValue(response, WeatherListDTO.class);
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

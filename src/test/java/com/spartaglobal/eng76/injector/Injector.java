package com.spartaglobal.eng76.injector;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spartaglobal.eng76.framework.dto.ParentDTO;
import com.spartaglobal.eng76.framework.dto.WeatherDTO;
import com.spartaglobal.eng76.framework.dto.WeatherListDTO;

public class Injector {

    private static ObjectMapper objectMapper = new ObjectMapper();
    private static ConnectionManager connectionManager = new ConnectionManager();

    public static WeatherDTO injectIntoWeatherDTO(String url) {
//        WeatherDTO weatherDTO = Factory.getDTO(url);
        connectionManager.connectToAPI(url);
        return objectMapper.readValue(connectionManager.getHttpResponseBody(), WeatherDTO.class);
    }

    public static WeatherListDTO injectIntoWeatherListDTO(String url) {
        connectionManager.connectToAPI(url);
        return objectMapper.readValue(ConnectionManager.getHttpResponse(url), WeatherListDTO.class);
    }

    public static ParentDTO injectIntoGenericDTO(String url) {
        connectionManager.connectToAPI(url);
        ParentDTO dtoChild = Factory.getDTO(url);
        return objectMapper.readValue(ConnectionManager.getHttpResponse(), dtoChild.class);
    }

}

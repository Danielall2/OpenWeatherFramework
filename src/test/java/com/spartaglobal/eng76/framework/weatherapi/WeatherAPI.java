package com.spartaglobal.eng76.framework.weatherapi;

import com.spartaglobal.eng76.framework.connectionmanager.ConnectionManager;
import com.spartaglobal.eng76.framework.dto.WeatherDTO;
import com.spartaglobal.eng76.framework.dto.WeatherListDTO;
import com.spartaglobal.eng76.framework.injector.Injector;
import com.spartaglobal.eng76.framework.urlbuilder.URLBuilder;

import java.util.Map;

public class WeatherAPI {

    public static ConnectionManager connectionManager = null;


    @SafeVarargs
    public static WeatherDTO ofCity(String cityName, String apikey, Map.Entry<String, String>... optionalParameters){
        URLBuilder urlBuilder = URLBuilder.ofCity(cityName,apikey);
        for(Map.Entry<String, String> optionalParam:optionalParameters){
            urlBuilder.addParam(optionalParam.getKey(), optionalParam.getValue());
        }
        connectionManager = new ConnectionManager();
        connectionManager.connectToAPI(urlBuilder.toString());
        System.out.println(urlBuilder.toString());
        return Injector.injectIntoWeatherDTO(connectionManager.getHttpResponseBody());
    }

    @SafeVarargs
    public static WeatherDTO ofCity(String cityName, String stateCode, String apikey, Map.Entry<String, String>... optionalParameters) {
        URLBuilder urlBuilder = URLBuilder.ofCity(cityName, stateCode, apikey);
        for(Map.Entry<String, String> optionalParam:optionalParameters){
            urlBuilder.addParam(optionalParam.getKey(), optionalParam.getValue());
        }
        connectionManager = new ConnectionManager();
        connectionManager.connectToAPI(urlBuilder.toString());
        return Injector.injectIntoWeatherDTO(connectionManager.getHttpResponseBody());
    }

    @SafeVarargs
    public static WeatherDTO ofCity(String cityName, String stateCode, String countryCode, String apikey, Map.Entry<String, String>... optionalParameters) {
        URLBuilder urlBuilder = URLBuilder.ofCity(cityName, stateCode, countryCode, apikey);
        for(Map.Entry<String, String> optionalParam:optionalParameters){
            urlBuilder.addParam(optionalParam.getKey(), optionalParam.getValue());
        }
        connectionManager = new ConnectionManager();
        connectionManager.connectToAPI(urlBuilder.toString());
        return Injector.injectIntoWeatherDTO(connectionManager.getHttpResponseBody());
    }

    @SafeVarargs
    public static WeatherDTO ofCity(int cityId, String apikey, Map.Entry<String, String>... optionalParameters) {
        URLBuilder urlBuilder = URLBuilder.ofCity(cityId, apikey);
        for(Map.Entry<String, String> optionalParam:optionalParameters){
            urlBuilder.addParam(optionalParam.getKey(), optionalParam.getValue());
        }
        connectionManager = new ConnectionManager();
        connectionManager.connectToAPI(urlBuilder.toString());
        return Injector.injectIntoWeatherDTO(connectionManager.getHttpResponseBody());
    }

    @SafeVarargs
    public static WeatherListDTO ofCities(int[] citiesId, String apikey, Map.Entry<String, String>... optionalParameters) {
        URLBuilder urlBuilder = URLBuilder.ofCities(citiesId, apikey);
        for(Map.Entry<String, String> optionalParam:optionalParameters){
            urlBuilder.addParam(optionalParam.getKey(), optionalParam.getValue());
        }
        connectionManager = new ConnectionManager();
        connectionManager.connectToAPI(urlBuilder.toString());
        return Injector.injectIntoWeatherListDTO(connectionManager.getHttpResponseBody());
    }

    @SafeVarargs
    public static WeatherListDTO ofCitiesInRectangle(long lon_left, long lat_bottom, long lon_right, long lat_top, int zoom, String apikey, Map.Entry<String, String>... optionalParameters) {
        URLBuilder urlBuilder = URLBuilder.ofCitiesInRectangle(lon_left, lat_bottom, lon_right, lat_top, zoom, apikey);
        for(Map.Entry<String, String> optionalParam:optionalParameters){
            urlBuilder.addParam(optionalParam.getKey(), optionalParam.getValue());
        }
        connectionManager = new ConnectionManager();
        connectionManager.connectToAPI(urlBuilder.toString());
        return Injector.injectIntoWeatherListDTO(connectionManager.getHttpResponseBody());
    }

    @SafeVarargs
    public static WeatherListDTO ofCitiesInCircle(long lat, long lon, String apikey, Map.Entry<String, String>... optionalParameters) {
        URLBuilder urlBuilder = URLBuilder.ofCitiesInCircle(lat, lon, apikey);
        for(Map.Entry<String, String> optionalParam:optionalParameters){
            urlBuilder.addParam(optionalParam.getKey(), optionalParam.getValue());
        }
        connectionManager = new ConnectionManager();
        connectionManager.connectToAPI(urlBuilder.toString());
        return Injector.injectIntoWeatherListDTO(connectionManager.getHttpResponseBody());
    }

    @SafeVarargs
    public static WeatherDTO ofZipCode(String zipCode, String apikey, Map.Entry<String, String>... optionalParameters) {
        URLBuilder urlBuilder = URLBuilder.ofZipCode(zipCode, apikey);
        for(Map.Entry<String, String> optionalParam:optionalParameters){
            urlBuilder.addParam(optionalParam.getKey(), optionalParam.getValue());
        }
        connectionManager = new ConnectionManager();
        connectionManager.connectToAPI(urlBuilder.toString());
        return Injector.injectIntoWeatherDTO(connectionManager.getHttpResponseBody());
    }

    @SafeVarargs
    public static WeatherDTO ofZipCode(String zipCode, String countryCode, String apikey, Map.Entry<String, String>... optionalParameters) {
        URLBuilder urlBuilder = URLBuilder.ofZipCode(zipCode, countryCode, apikey);
        for(Map.Entry<String, String> optionalParam:optionalParameters){
            urlBuilder.addParam(optionalParam.getKey(), optionalParam.getValue());
        }
        connectionManager = new ConnectionManager();
        connectionManager.connectToAPI(urlBuilder.toString());
        return Injector.injectIntoWeatherDTO(connectionManager.getHttpResponseBody());
    }

}

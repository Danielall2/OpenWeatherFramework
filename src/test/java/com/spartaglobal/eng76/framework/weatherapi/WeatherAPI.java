package com.spartaglobal.eng76.framework.weatherapi;

import com.spartaglobal.eng76.framework.connectionmanager.ConnectionManager;
import com.spartaglobal.eng76.framework.dto.Enums.Main;
import com.spartaglobal.eng76.framework.dto.WeatherDTO;
import com.spartaglobal.eng76.framework.dto.WeatherListDTO;
import com.spartaglobal.eng76.framework.injector.Injector;
import com.spartaglobal.eng76.framework.urlbuilder.URLBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class WeatherAPI {

    public static ConnectionManager connectionManager = null;

    public static WeatherDTO ofCity(String cityName, String apikey) {
        connectionManager = new ConnectionManager();
        connectionManager.connectToAPI(URLBuilder.ofCity(cityName, apikey).toString());
        return Injector.injectIntoWeatherDTO(connectionManager.getHttpResponseBody());
    }

    public static WeatherDTO ofCity(String cityName, String stateCode, String apikey) {
        connectionManager = new ConnectionManager();
        connectionManager.connectToAPI(URLBuilder.ofCity(cityName, stateCode, apikey).toString());
        return Injector.injectIntoWeatherDTO(connectionManager.getHttpResponseBody());
    }

    public static WeatherDTO ofCity(String cityName, String stateCode, String countryCode, String apikey) {
        connectionManager = new ConnectionManager();
        connectionManager.connectToAPI(URLBuilder.ofCity(cityName, stateCode, countryCode, apikey).toString());
        return Injector.injectIntoWeatherDTO(connectionManager.getHttpResponseBody());
    }

    public static WeatherDTO ofCity(int cityId, String apikey) {
        connectionManager = new ConnectionManager();
        connectionManager.connectToAPI(URLBuilder.ofCity(cityId, apikey).toString());
        return Injector.injectIntoWeatherDTO(connectionManager.getHttpResponseBody());
    }

    public static WeatherListDTO ofCities(int[] citiesId, String apikey) {
        connectionManager = new ConnectionManager();
        connectionManager.connectToAPI(URLBuilder.ofCities(citiesId, apikey).toString());
        return Injector.injectIntoWeatherListDTO(connectionManager.getHttpResponseBody());
    }

    public static WeatherListDTO ofCitiesInRectangle(long lon_left, long lat_bottom, long lon_right, long lat_top, int zoom, String apikey) {
        connectionManager = new ConnectionManager();
        connectionManager.connectToAPI(URLBuilder.ofCitiesInRectangle(lon_left, lat_bottom, lon_right, lat_top, zoom, apikey).toString());
        return Injector.injectIntoWeatherListDTO(connectionManager.getHttpResponseBody());
    }

    public static WeatherListDTO ofCitiesInCircle(long lat, long lon, String apikey) {
        connectionManager = new ConnectionManager();
        connectionManager.connectToAPI(URLBuilder.ofCitiesInCircle(lat, lon, apikey).toString());
        return Injector.injectIntoWeatherListDTO(connectionManager.getHttpResponseBody());
    }

    public static WeatherDTO ofZipCode(String zipCode, String apikey) {
        connectionManager = new ConnectionManager();
        connectionManager.connectToAPI(URLBuilder.ofZipCode(zipCode, apikey).toString());
        return Injector.injectIntoWeatherDTO(connectionManager.getHttpResponseBody());
    }

    public static WeatherDTO ofZipCode(String zipCode, String countryCode, String apikey) {
        connectionManager = new ConnectionManager();
        connectionManager.connectToAPI(URLBuilder.ofZipCode(zipCode, countryCode, apikey).toString());
        return Injector.injectIntoWeatherDTO(connectionManager.getHttpResponseBody());
    }

}

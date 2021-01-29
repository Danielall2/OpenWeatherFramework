package com.spartaglobal.eng76.framework.apitesting;

import com.spartaglobal.eng76.framework.connectionmanager.ConnectionManager;
import com.spartaglobal.eng76.framework.dto.Enums.Main;
import com.spartaglobal.eng76.framework.dto.Enums.Wind;
import com.spartaglobal.eng76.framework.dto.WeatherDTO;
import com.spartaglobal.eng76.framework.dto.WeatherListDTO;
import com.spartaglobal.eng76.framework.exceptions.FailedHttpConnectionException;
import com.spartaglobal.eng76.framework.injector.Injector;
import com.spartaglobal.eng76.framework.urlbuilder.URLBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class BoundaryValuesTests {
    Properties properties = new Properties();

    @BeforeEach
    public void setup(){
        try {
            properties.load(new FileReader("src/test/resources/apikey.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void CheckReturnedCitiesInRectangle() throws FailedHttpConnectionException {
        //todo
        String url = URLBuilder.ofCitiesInRectangle(12,32,17,37,10, properties.getProperty("apikey")).toString();
        ConnectionManager connectionManager = new ConnectionManager();
        connectionManager.connectToAPI(url);
        try {
            WeatherListDTO weatherListDTO = Injector.injectIntoWeatherListDTO(connectionManager);
        } catch (FailedHttpConnectionException e) {
            e.printStackTrace();
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"London"})
    public void tempMaxBoundary(String location) throws FailedHttpConnectionException {
        WeatherDTO weatherDTO = getWeatherDTO(location);
        Assertions.assertTrue(stringToDouble(weatherDTO.getMain().get(Main.TEMPERATURE.toString())) < stringToDouble(weatherDTO.getMain().get(Main.MAX_TEMPERATURE.toString())));
    }

    @ParameterizedTest
    @ValueSource(strings = {"London"})
    public void tempMinBoundary(String location) throws FailedHttpConnectionException {
        WeatherDTO weatherDTO = getWeatherDTO(location);
        Assertions.assertTrue(stringToDouble(weatherDTO.getMain().get(Main.TEMPERATURE.toString())) > stringToDouble(weatherDTO.getMain().get(Main.MIN_TEMPERATURE.toString())));
    }

    @ParameterizedTest
    @ValueSource(strings = {"London"})
    public void humidityLessThan100(String location) throws FailedHttpConnectionException {
        WeatherDTO weatherDTO = getWeatherDTO(location);
        Assertions.assertTrue(stringToDouble(weatherDTO.getMain().get(Main.HUMIDITY.toString())) <= 100);
    }

    @ParameterizedTest
    @ValueSource(strings = {"London"})
    public void humidityMoreThan0(String location) throws FailedHttpConnectionException {
        WeatherDTO weatherDTO = getWeatherDTO(location);
        Assertions.assertTrue(stringToDouble(weatherDTO.getMain().get(Main.HUMIDITY.toString())) >= 0);
    }

    @ParameterizedTest
    @ValueSource(strings = {"London"})
    public void visibilityMaxBoundary(){
        //todo
    }

    @ParameterizedTest
    @ValueSource(strings = {"London"})
    public void visibilityMinBoundary(String location) throws FailedHttpConnectionException {
        WeatherDTO weatherDTO = getWeatherDTO(location);
        Assertions.assertTrue(stringToDouble(weatherDTO.getVisibility().toString()) > 0);
    }

    @ParameterizedTest
    @ValueSource(strings = {"London"})
    public void windPresent(String location) throws FailedHttpConnectionException {
        WeatherDTO weatherDTO = getWeatherDTO(location);
        Assertions.assertTrue(stringToDouble(weatherDTO.getWind().get(Wind.SPEED.toString())) != Double.parseDouble(null));
        Assertions.assertTrue(stringToDouble(weatherDTO.getWind().get(Wind.DEGREES.toString())) != Double.parseDouble(null));
        Assertions.assertTrue(stringToDouble(weatherDTO.getWind().get(Wind.GUST.toString())) != Double.parseDouble(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"London"})
    public void windBearingMoreThanZero(String location) throws FailedHttpConnectionException {
        WeatherDTO weatherDTO = getWeatherDTO(location);
        Assertions.assertTrue(stringToDouble(weatherDTO.getWind().get(Wind.DEGREES.toString())) >= 0);
    }

    @ParameterizedTest
    @ValueSource(strings = {"London"})
    public void windBearingLessThan360(String location) throws FailedHttpConnectionException {
        WeatherDTO weatherDTO = getWeatherDTO(location);
        Assertions.assertTrue(stringToDouble(weatherDTO.getWind().get(Wind.DEGREES.toString())) < 360);
    }

    @ParameterizedTest
    @ValueSource(strings = {"London"})
    public void gustFasterThanWind(String location) throws FailedHttpConnectionException {
        WeatherDTO weatherDTO = getWeatherDTO(location);
        Assertions.assertTrue(stringToDouble(weatherDTO.getWind().get(Wind.SPEED.toString())) < stringToDouble(weatherDTO.getWind().get(Wind.GUST.toString()).toString()));
    }

    private WeatherDTO getWeatherDTO(String location) throws FailedHttpConnectionException {
        String url = URLBuilder.ofCity(location,properties.getProperty("apikey")).toString();
        ConnectionManager connectionManager = new ConnectionManager();
        connectionManager.connectToAPI(url);
        WeatherDTO weatherDTO = null;
        try {
            weatherDTO = Injector.injectIntoWeatherDTO(connectionManager);
        } catch (FailedHttpConnectionException e) {
            e.printStackTrace();
        }
        return weatherDTO;
    }

    private double stringToDouble(String number){
        double ret = Double.parseDouble(number);
        return ret;
    }
}

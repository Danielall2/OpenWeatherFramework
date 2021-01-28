package com.spartaglobal.eng76.frameworktests;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.spartaglobal.eng76.framework.connectionmanager.ConnectionManager;
import com.spartaglobal.eng76.framework.dto.WeatherDTO;
import com.spartaglobal.eng76.framework.dto.WeatherListDTO;
import com.spartaglobal.eng76.framework.exceptions.FailedHttpConnectionException;
import com.spartaglobal.eng76.framework.injector.Injector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public class InjectorTests {

    private static Properties properties;
    private static ConnectionManager connectionManagerByCity;
    private static ConnectionManager connectionManagerByCityID;
    private static ConnectionManager connectionManagerByCoord;
    private static ConnectionManager connectionManagerByZip;
    private static ConnectionManager connectionManagerListByBox;
    private static ConnectionManager connectionManagerListByCircle;
    private static ConnectionManager connectionManagerListByGroup;


    @BeforeAll
    static void setup() {
        properties = new Properties();
        connectionManagerByCity = new ConnectionManager();
        connectionManagerByCityID = new ConnectionManager();
        connectionManagerByCoord = new ConnectionManager();
        connectionManagerByZip = new ConnectionManager();
        connectionManagerListByBox = new ConnectionManager();
        connectionManagerListByCircle = new ConnectionManager();
        connectionManagerListByGroup = new ConnectionManager();
        try {
            properties.load(new FileReader("src/test/resources/apikey.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        connectionManagerByCity.connectToAPI("https://api.openweathermap.org/data/2.5/weather?q=London&appid=" + properties.getProperty("apikey"));
        connectionManagerByCityID.connectToAPI("https://api.openweathermap.org/data/2.5/weather?id=2172797&appid=" + properties.getProperty("apikey"));
        connectionManagerByCoord.connectToAPI("https://api.openweathermap.org/data/2.5/weather?lat=35&lon=139&appid=" + properties.getProperty("apikey"));
        connectionManagerByZip.connectToAPI("https://api.openweathermap.org/data/2.5/weather?zip=94040,us&appid=" + properties.getProperty("apikey"));
        connectionManagerListByBox.connectToAPI("https://api.openweathermap.org/data/2.5/box/city?bbox=12,32,15,37,10&appid=" + properties.getProperty("apikey"));
        connectionManagerListByCircle.connectToAPI("https://api.openweathermap.org/data/2.5/find?lat=55.5&lon=37.5&cnt=10&appid=" + properties.getProperty("apikey"));
        connectionManagerListByGroup.connectToAPI("https://api.openweathermap.org/data/2.5/group?id=524901,703448,2643743&appid=" + properties.getProperty("apikey"));
    }

    @Test
    @DisplayName("WeatherDTO Injector returns correct object by city")
    void returnsObjectOfCorrectTypeByCity() {
        try {
            Assertions.assertEquals(WeatherDTO.class, Injector.injectIntoWeatherDTO(connectionManagerByCity).getClass());
        } catch (FailedHttpConnectionException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("WeatherDTO Injector returns correct object by cityID")
    void returnsObjectOfCorrectTypeByCityID() {
        try {
            Assertions.assertEquals(WeatherDTO.class, Injector.injectIntoWeatherDTO(connectionManagerByCityID).getClass());
        } catch (FailedHttpConnectionException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("WeatherDTO Injector returns correct object by co-ordinates")
    void returnsObjectOfCorrectTypeByCoord() {
        try {
            Assertions.assertEquals(WeatherDTO.class, Injector.injectIntoWeatherDTO(connectionManagerByCoord).getClass());
        } catch (FailedHttpConnectionException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("WeatherDTO Injector returns correct object by Zip")
    void returnsObjectOfCorrectTypeByZip() {
        try {
            Assertions.assertEquals(WeatherDTO.class, Injector.injectIntoWeatherDTO(connectionManagerByZip).getClass());
        } catch (FailedHttpConnectionException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("WeatherDTO Injector throws exception from null")
    void weatherDTOThrowsExceptionFromNull() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Injector.injectIntoWeatherDTO(null);
        });
    }

    @Test
    @DisplayName("WeatherListDTO Injector returns correct object by box")
    void listDTOReturnsObjectOfCorrectTypeByBox() {
        try {
            Assertions.assertEquals(WeatherListDTO.class, Injector.injectIntoWeatherListDTO(connectionManagerListByBox).getClass());
        } catch (FailedHttpConnectionException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("WeatherListDTO Injector returns correct object by circle")
    void listDTOReturnsObjectOfCorrectTypeByCircle() {
        try {
            Assertions.assertEquals(WeatherListDTO.class, Injector.injectIntoWeatherListDTO(connectionManagerListByCircle).getClass());
        } catch (FailedHttpConnectionException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("WeatherListDTO Injector returns correct object by group")
    void listDTOReturnsObjectOfCorrectTypeByGroup() {
        try {
            Assertions.assertEquals(WeatherListDTO.class, Injector.injectIntoWeatherListDTO(connectionManagerListByGroup).getClass());
        } catch (FailedHttpConnectionException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("WeatherListDTO Injector throws exception from null")
    void weatherListDTOThrowsExceptionFromNull() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Injector.injectIntoWeatherListDTO(null);
        });
    }

}

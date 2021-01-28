package com.spartaglobal.eng76.framework.apitesting;

import com.spartaglobal.eng76.framework.connectionmanager.ConnectionManager;
import com.spartaglobal.eng76.framework.urlbuilder.URLBuilder;
import com.spartaglobal.eng76.framework.weatherapi.WeatherAPI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConnectionTests {

    ConnectionManager connectionManager = new ConnectionManager();

    @BeforeEach
    void setup() {

    }

    @ParameterizedTest
    @CsvSource({"Sydney", "London", "Toronto", "Tokyo"})
    @DisplayName("Check valid url gives status code 200")
    void checkValidUrlGivesStatusCode200(String city) {
        String url = URLBuilder.ofCity(city, WeatherAPI.getAPIKey()).toString();
        connectionManager.connectToAPI(url);
        Assertions.assertEquals(200, connectionManager.getStatusCode());
    }

    @ParameterizedTest
    @CsvSource({"Sydregney", "Lowdon", "Torontrhto", "Toyko", "L"})
    @DisplayName("Check invalid url gives status code 404")
    void checkInvalidUrlGivesStatusCode404(String city) {
        String url = URLBuilder.ofCity(city, WeatherAPI.getAPIKey()).toString();
        connectionManager.connectToAPI(url);
        Assertions.assertEquals(404, connectionManager.getStatusCode());
    }

    @Test
    @DisplayName("Check false apikey gives status code 401")
    void checkFalseApikeyGivesStatusCode401() {
        String fakeAPIKey = "gudne48fhe634h6ghewgd633hdyftdeh";
        String url = URLBuilder.ofCity("London", fakeAPIKey).toString();
        connectionManager.connectToAPI(url);
        Assertions.assertEquals(401, connectionManager.getStatusCode());
    }

    @ParameterizedTest
    @ValueSource(ints = {0000000, 0, 0000} )
    @DisplayName("Check incorrect city id returns 400")
    void checkIncorrectCityIdReturns400(int badCityId) {
        String url = URLBuilder.ofCity(badCityId, WeatherAPI.getAPIKey()).toString();
        connectionManager.connectToAPI(url);
        Assertions.assertEquals(400, connectionManager.getStatusCode());
    }

    @Test
    @DisplayName("Is httpResponse returned as an object")
    void isHttpResponseReturnedAsAnObject() {
        String url = URLBuilder.ofCity("London", WeatherAPI.getAPIKey()).toString();
        connectionManager.connectToAPI(url);
        Assertions.assertNotNull(connectionManager.getHttpResponseBody());
    }

    @Test
    @DisplayName("Check response time was less than 10 seconds")
    void checkResponseTimeWasLessThan10Seconds() {
        long startTime = System.currentTimeMillis();
        String url = URLBuilder.ofCity("London", WeatherAPI.getAPIKey()).toString();
        connectionManager.connectToAPI(url);
        long totalTime = System.currentTimeMillis() - startTime;
        System.out.println(totalTime);
        Assertions.assertTrue(totalTime < 10000);
    }

//    @Test
//    @DisplayName("Check responses are limited to 60 per minute")
//    void checkResponsesAreLimitedTo60PerMinute() {
//        int counter = 0;
//        for (int i = 0; i < 100; i++) {
//            String url = URLBuilder.ofCity("London", properties.getProperty("Sidhant_apikey")).toString();
//            connectionManager.connectToAPI(url);
//            System.out.println(connectionManager.getStatusCode());
//            System.out.println(connectionManager.getHttpResponseBody().toString());
//            counter++;
//        }
//        System.out.println(counter);
//    }


}

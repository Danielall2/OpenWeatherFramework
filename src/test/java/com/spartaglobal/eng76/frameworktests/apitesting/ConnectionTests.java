package com.spartaglobal.eng76.framework.apitesting;

import com.spartaglobal.eng76.framework.connectionmanager.ConnectionManager;
import com.spartaglobal.eng76.framework.urlbuilder.URLBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConnectionTests {
    Properties properties = new Properties();
    ConnectionManager connectionManager = new ConnectionManager();

    @BeforeEach
    void setup() {
        try {
            properties.load(new FileReader("src/test/resources/apikey.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Check valid url gives status code 200")
    void checkValidUrlGivesStatusCode200() {
        String url = URLBuilder.ofCity("London", properties.getProperty("apikey")).toString();
        connectionManager.connectToAPI(url);
        Assertions.assertEquals(200, connectionManager.getStatusCode());
    }

    @Test
    @DisplayName("Check invalid url gives status code 404")
    void checkInvalidUrlGivesStatusCode404() {
        String url = URLBuilder.ofCity("L", properties.getProperty("apikey")).toString();
        connectionManager.connectToAPI(url);
        Assertions.assertEquals(404, connectionManager.getStatusCode());
    }

    @Test
    @DisplayName("Check false apikey gives status code 401")
    void checkFalseApikeyGivesStatusCode401() {
        String url = URLBuilder.ofCity("London", properties.getProperty("faulty_apikey")).toString();
        connectionManager.connectToAPI(url);
        Assertions.assertEquals(401, connectionManager.getStatusCode());
    }

    @Test
    @DisplayName("Check incorrect city id returns 400")
    void checkIncorrectCityIdReturns400() {
        String url = URLBuilder.ofCity(0000000, properties.getProperty("apikey")).toString();
        connectionManager.connectToAPI(url);
        Assertions.assertEquals(400, connectionManager.getStatusCode());
    }

    @Test
    @DisplayName("Is httpResponse returned as an object")
    void isHttpResponseReturnedAsAnObject() {
        String url = URLBuilder.ofCity("London", properties.getProperty("apikey")).toString();
        connectionManager.connectToAPI(url);
        Assertions.assertNotNull(connectionManager.getHttpResponseBody());
    }

    @Test
    @DisplayName("Check response time was less than 10 seconds")
    void checkResponseTimeWasLessThan10Seconds() {
        long startTime = System.currentTimeMillis();
        String url = URLBuilder.ofCity("London", properties.getProperty("apikey")).toString();
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

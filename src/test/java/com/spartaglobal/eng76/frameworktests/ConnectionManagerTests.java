package com.spartaglobal.eng76.frameworktests;

import com.spartaglobal.eng76.framework.connectionmanager.ConnectionManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConnectionManagerTests {
    private static Properties properties;
    private static ConnectionManager connectionManager;

    @BeforeAll
    static void setup(){
         properties = new Properties();
         connectionManager = new ConnectionManager();
        try {
            properties.load(new FileReader("src/test/resources/apikey.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        connectionManager.connectToAPI("https://api.openweathermap.org/data/2.5/weather?id=2172797&appid=" + properties.getProperty("apikey"));

    }



    @Test
    void checkStatusCode200(){
        Assertions.assertEquals(connectionManager.getStatusCode(), 200);
    }


    @Test
    void checkJSONIsReturned(){
         String json = connectionManager.getHttpResponseBody();
         Assertions.assertTrue(json.startsWith("{"));
    }


    @Test
    void checkCorrectHTTPVersion(){
        Assertions.assertEquals(connectionManager.getHttpVersion(), "HTTP_1_1");
    }


    @Test
    void checkStatusCode404(){
        connectionManager.connectToAPI("https://api.openweathermap.org/data/2.5/weather?id=99999999&appid=" + properties.getProperty("apikey"));
        Assertions.assertEquals(connectionManager.getStatusCode(), 404);
    }


    @Test
    void checkStatusCode400(){
        connectionManager.connectToAPI("https://api.openweathermap.org/data/2.5/weather?id=55.05&appid=" + properties.getProperty("apikey"));
        Assertions.assertEquals(connectionManager.getStatusCode(), 400);
    }

}

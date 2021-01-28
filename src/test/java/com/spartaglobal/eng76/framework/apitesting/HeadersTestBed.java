package com.spartaglobal.eng76.framework.apitesting;

import com.spartaglobal.eng76.framework.connectionmanager.ConnectionManager;
import com.spartaglobal.eng76.framework.urlbuilder.URLBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.FileReader;
import java.io.IOException;
import java.net.http.HttpHeaders;
import java.util.Properties;

public class HeadersTestBed {

    HttpHeaders httpHeaders = null;
    Properties properties = new Properties();

    /*
     * testing not complete
     */

    @BeforeEach
    public void setup(){
        try {
            properties.load(new FileReader("src/test/resources/apikey.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @ParameterizedTest
    @ValueSource(strings = {"London", "paris"})//contains random urls to return headers
    public void CheckHeadersAreReturnedFromRequests(String location){
        httpHeaders = sortOutConnection(location);
        Assertions.assertEquals(9,httpHeaders.map().size());
    }

    @ParameterizedTest
    @ValueSource(strings = {"London", "paris"})
    public void checkConnectionsHeaderReturnsKeepAlive(String location){
        httpHeaders = sortOutConnection(location);
        Assertions.assertTrue(httpHeaders.map().get("Connection").toString().contains("keep-alive"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"London", "paris"})
    public void checkConnectionIsJson(String location){
        httpHeaders = sortOutConnection(location);
        Assertions.assertTrue(httpHeaders.map().get("Content-Type").toString().contains("json"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"London", "paris"})
    public void checkServer(String location){
        httpHeaders = sortOutConnection(location);
        Assertions.assertTrue(httpHeaders.map().get("Server").toString().contains("openresty"));
    }

    @Test
    public void checkDate(){
        //Assertions.assertTrue(httpHeaders.map().get("Date") == LocalDate.now().toString());
        //Assertions.assertTrue(httpHeaders.map().get("Date").toString().contains(LocalDate.now().format().toString()));
    }

    private HttpHeaders sortOutConnection(String location){
        String url = URLBuilder.ofCity(location, properties.getProperty("apikey")).toString();
        ConnectionManager connectionManager = new ConnectionManager();
        connectionManager.connectToAPI(url);
        return connectionManager.getHttpHeaders();
    }
}

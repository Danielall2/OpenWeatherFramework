package com.spartaglobal.eng76.framework.apitesting;

import com.spartaglobal.eng76.framework.connectionmanager.ConnectionManager;
import com.spartaglobal.eng76.framework.urlbuilder.URLBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.FileReader;
import java.io.IOException;
import java.net.http.HttpHeaders;
import java.time.LocalDate;
import java.util.Properties;

public class HeadersTestBed {

    HttpHeaders httpHeaders = null;
    String BaseURL = null;

    @BeforeAll
    public void initially(){
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("src/test/resources/apikey.properties"));
            BaseURL = URLBuilder.ofCity("London", properties.getProperty("apikey")).toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ConnectionManager connectionManager = new ConnectionManager();
        connectionManager.connectToAPI(BaseURL);
        httpHeaders = connectionManager.getHttpHeaders;

    }

    @BeforeEach
    public void setup(){

    }


    @ParameterizedTest
    @ValueSource(urls = {})//contains random urls to return headers
    public void CheckHeadersAreReturnedFromRequests(String url){
        Assertions.assertEquals(9,httpHeaders.map().size());
    }

    @ParameterizedTest
    @ValueSource()
    public void checkConnectionsHeaderReturnsKeepAlive(String URL){
        Assertions.assertEquals("keep-alive",httpHeaders.map().get("Connection"));
    }

    @ParameterizedTest
    @ValueSource()
    public void checkConnectionIsJson(String URL){
        Assertions.assertTrue(httpHeaders.map().get("Content-Type").contains("json"));
    }

    @ParameterizedTest
    @ValueSource()
    public void checkServer(String URL){
        Assertions.assertEquals("openresty",httpHeaders.map().get("Server"));
    }

    @Test
    public void checkDate(){
        Assertions.assertTrue(httpHeaders.map().get("Date") == LocalDate.now().toString());
    }

}

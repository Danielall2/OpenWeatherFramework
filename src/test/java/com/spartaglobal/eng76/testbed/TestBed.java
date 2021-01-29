package com.spartaglobal.eng76.testbed;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spartaglobal.eng76.framework.exceptions.FailedHttpConnectionException;
import com.spartaglobal.eng76.framework.weatherapi.WeatherAPI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class TestBed {

    @Test
    @DisplayName("Get City by name - With framework")
    void getCityByNameShort() throws FailedHttpConnectionException {
        List<HashMap<String, String>> weather = WeatherAPI.ofCity("London", WeatherAPI.getAPIKey()).getWeather();
        System.out.println(weather);
    }

    @Test
    @DisplayName("Get City by name - Without framework")
    void getCityByNameLong() throws IOException, InterruptedException {
        String url = "https://api.openweathermap.org/data/2.5/weather?q=London&appid=";
        Properties properties = new Properties();
        properties.load(new FileReader("src/test/resources/apikey.properties"));
        url = url + properties.getProperty("apikey");
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        JsonNode jsonNode = new ObjectMapper().readTree(httpResponse.body());
        Assertions.assertEquals(jsonNode.get("weather").get(0).get("id").toString(), "502");
    }

}

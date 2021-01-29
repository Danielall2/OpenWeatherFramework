package com.spartaglobal.eng76.testbed;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spartaglobal.eng76.framework.dto.WeatherDTO;
import com.spartaglobal.eng76.framework.dto.WeatherListDTO;
import com.spartaglobal.eng76.framework.exceptions.FailedHttpConnectionException;
import com.spartaglobal.eng76.framework.urlbuilder.OptionalParam;
import com.spartaglobal.eng76.framework.weatherapi.WeatherAPI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class TestBed {

    static WeatherDTO weatherDTO;

    @BeforeAll
    @DisplayName("Setting up weatherDTO")
    static void setup() throws FailedHttpConnectionException {
        weatherDTO = WeatherAPI.ofCity("London", WeatherAPI.getAPIKey());
    }

    @Test
    @DisplayName("Getting status code with framework")
    void checkStatusCodeWithFramework(){
        Assertions.assertEquals(200, weatherDTO.getConnectionManager().getStatusCode());
    }

    @Test
    @DisplayName("Getting status code without framework")
    void checkStatusCodeWithoutFramework()  throws IOException, InterruptedException{
        String url = "https://api.openweathermap.org/data/2.5/weather?q=London&appid=";
        Properties properties = new Properties();
        properties.load(new FileReader("src/test/resources/apikey.properties"));
        url = url + properties.getProperty("apikey");
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        Assertions.assertEquals(200, httpResponse.statusCode());
    }

    @Test
    @DisplayName("Getting the amount of headers with the framework")
    void checkHeadersAreReturnedFromRequests(){
        int size = weatherDTO.getConnectionManager().getAllResponseHeaders().map().size();
        Assertions.assertEquals(9,size);
    }

    @Test
    @DisplayName("Getting the connection header with the framework")
    void checkConnectionsHeaderReturnsKeepAlive(){
        String connection = weatherDTO.getConnectionManager().getAllResponseHeaders().map().get("Connection").toString();
        Assertions.assertEquals(connection, "[keep-alive]");
    }

    @Test
    @DisplayName("Getting the content type header with the framework")
    void checkConnectionIsJson(){
        String json = weatherDTO.getConnectionManager().getAllResponseHeaders().map().get("Content-Type").toString();
        Assertions.assertEquals(json, "[application/json; charset=utf-8]");
    }

    @Test
    @DisplayName("Getting the server header with the framework")
    void checkServer(){
        String server = weatherDTO.getConnectionManager().getAllResponseHeaders().map().get("Server").toString();
        Assertions.assertEquals(server, "[openresty]");
    }

    @Test
    @DisplayName("Getting the headers without the framework")
    void checkHeadersWithoutFramework() throws IOException, InterruptedException{
        String url = "https://api.openweathermap.org/data/2.5/weather?q=London&appid=";
        Properties properties = new Properties();
        properties.load(new FileReader("src/test/resources/apikey.properties"));
        url = url + properties.getProperty("apikey");
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        String connection = httpResponse.headers().map().get("Connection").toString();
        Assertions.assertEquals(connection, "[keep-alive]");
    }

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
    }

    @Test
    @DisplayName("Get City by ID - With framework")
    void getCityByIDShort() throws FailedHttpConnectionException {
        List<HashMap<String, String>> weather = WeatherAPI.ofCity(2172797, WeatherAPI.getAPIKey()).getWeather();
        System.out.println(weather);
    }

    @Test
    @DisplayName("Get City by ID - Without framework")
    void getCityByIDLong() throws IOException, InterruptedException {
        String url = "https://api.openweathermap.org/data/2.5/weather?id=2172797&appid=";
        Properties properties = new Properties();
        properties.load(new FileReader("src/test/resources/apikey.properties"));
        url = url + properties.getProperty("apikey");
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        JsonNode jsonNode = new ObjectMapper().readTree(httpResponse.body());
    }

    @Test
    @DisplayName("Get City by ZIP - With framework")
    void getCityByZipShort() throws FailedHttpConnectionException {
        List<HashMap<String, String>> weather = WeatherAPI.ofZipCode("94040", WeatherAPI.getAPIKey()).getWeather();
        System.out.println(weather);
    }

    @Test
    @DisplayName("Get City by Zip - Without framework")
    void getCityByZipLong() throws IOException, InterruptedException {
        String url = "https://api.openweathermap.org/data/2.5/weather?zip=94040,us&appid=";
        Properties properties = new Properties();
        properties.load(new FileReader("src/test/resources/apikey.properties"));
        url = url + properties.getProperty("apikey");
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        JsonNode jsonNode = new ObjectMapper().readTree(httpResponse.body());
    }

    @Test
    @DisplayName("WITH: getting data in rectangle and checking if the number of cities returned match with real data")
    void withGettingDataInRectangle() throws FailedHttpConnectionException {
        WeatherListDTO weatherListDTO = WeatherAPI.ofCitiesInRectangle(
                -2, 51,
                0, 53,
                10, WeatherAPI.getAPIKey());
        Assertions.assertEquals("76", weatherListDTO.getCnt());
    }

    @Test
    @DisplayName("WITHOUT: getting data in rectangle and checking if the number of cities returned match with real data")
    void withoutGettingDataInRectangle() throws IOException, InterruptedException {
        String url = "https://api.openweathermap.org/data/2.5/box/city?bbox=-2.0,51.0,0.0,53.0,10&appid=";
        Properties properties = new Properties();
        properties.load(new FileReader("src/test/resources/apikey.properties"));
        url = url + properties.getProperty("apikey");
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        JsonNode jsonNode = new ObjectMapper().readTree(httpResponse.body());
        Assertions.assertEquals("76", jsonNode.get("cnt").toString());
    }

    @Test
    @DisplayName("WITH: getting data in circle, adding optional parameters and checking the number of cities")
    void withGettingDataInCircleAddingOptionalParametersAndCheckingTheNumberOfCities() throws FailedHttpConnectionException {
        String response = WeatherAPI.ofCitiesInCircle(55, 37.5, WeatherAPI.getAPIKey(), OptionalParam.COUNT.ofValue("10")).getCount();
        Assertions.assertEquals("10", response);
    }

    @Test
    @DisplayName("WITHOUT: getting data in circle, adding optional parameters and checking the number of cities")
    void withoutGettingDataInCircleAddingOptionalParametersAndCheckingTheNumberOfCities() throws IOException, InterruptedException {
        String url = "https://api.openweathermap.org/data/2.5/find?lat=55.0&lon=37.5&cnt=10&appid=";
        Properties properties = new Properties();
        properties.load(new FileReader("src/test/resources/apikey.properties"));
        url = url + properties.getProperty("apikey");
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        JsonNode jsonNode = new ObjectMapper().readTree(httpResponse.body());
        Assertions.assertEquals("10", jsonNode.get("count").toString());
    }
}

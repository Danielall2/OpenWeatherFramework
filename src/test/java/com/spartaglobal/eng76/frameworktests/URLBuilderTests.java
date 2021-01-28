package com.spartaglobal.eng76.frameworktests;

import com.spartaglobal.eng76.framework.urlbuilder.URLBuilder;
import com.spartaglobal.eng76.framework.weatherapi.WeatherAPI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class URLBuilderTests {
    private static Properties properties;


    @BeforeAll
    static void setup(){
        properties = new Properties();

        try {
            properties.load(new FileReader("src/test/resources/apikey.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Test
    void checkCreateCanHandleNull(){
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {URLBuilder.create(null, null);});

        String actual = exception.getMessage();
        String expected = "Null values not allowed!";

        Assertions.assertEquals(actual, expected);


    }

    @Test
    void checkCreateCanHandleEmptyKey(){
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {URLBuilder.create("https://api.openweathermap.org/data/2.5/weather?id=2172797", "");});

        String actual = exception.getMessage();
        String expected = "Missing API key";

        Assertions.assertEquals(actual, expected);
    }


    @Test
    void checkCreateCanHandleEmptyURL(){


        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {URLBuilder.create("", WeatherAPI.getAPIKey());});

        String actual = exception.getMessage();
        String expected = "Bad URL format.";

        Assertions.assertEquals(actual, expected);

    }

    @Test
    void checkOfCityCanHandleNull(){

        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {URLBuilder.ofCity(null, WeatherAPI.getAPIKey());});

        String actual = exception.getMessage();
        String expected = "Null values not allowed!";

        Assertions.assertEquals(actual, expected);

    }

    @Test
    @DisplayName("Checking first ofCity method returns correct String")
    void checkOfCityOne(){
        String expected = "https://api.openweathermap.org/data/2.5/weather?q=London&appid=" + WeatherAPI.getAPIKey();
        String actual = URLBuilder.ofCity("London", WeatherAPI.getAPIKey()).toString();


        Assertions.assertEquals(expected,actual);
    }


    @Test
    @DisplayName("Check second ofCity method returns correct String")
    void checkOfCityTwo(){


        String expected = "https://api.openweathermap.org/data/2.5/weather?q=London,3&appid=" + WeatherAPI.getAPIKey();
        String actual = URLBuilder.ofCity("London", "3",WeatherAPI.getAPIKey()).toString();


        Assertions.assertEquals(expected,actual);

    }


    @Test
    @DisplayName("Check third ofCity method returns correct String")
    void checkOfCityThree(){
        String expected = "https://api.openweathermap.org/data/2.5/weather?q=London,3,10&appid=" + WeatherAPI.getAPIKey();
        String actual = URLBuilder.ofCity("London", "3","10",WeatherAPI.getAPIKey()).toString();


        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Check fourth ofCity method returns correct String")
    void checkOfCityFour(){
        String expected = "https://api.openweathermap.org/data/2.5/weather?q=5405&appid=" + WeatherAPI.getAPIKey();
        String actual = URLBuilder.ofCity("5405", WeatherAPI.getAPIKey()).toString();


        Assertions.assertEquals(expected,actual);
    }


    @Test
    @DisplayName("Check ofCities returns correct String")
    void checkOfCities(){

        String expected = "https://api.openweathermap.org/data/2.5/group?id=1,2,3,4&appid=" + WeatherAPI.getAPIKey();

        int[] ids = new int[]{1,2,3,4};
        String actual = URLBuilder.ofCities(ids, WeatherAPI.getAPIKey()).toString();


        Assertions.assertEquals(expected,actual);

    }


    @Test
    @DisplayName("Check ofCitiesInRectangle returns correct String")
    void checkOfCitiesInRectangle(){


        String expected = "https://api.openweathermap.org/data/2.5/box/city?bbox=1,2,3,4,5&appid=" + WeatherAPI.getAPIKey();
        String actual = URLBuilder.ofCitiesInRectangle(1,2,3,4,5, WeatherAPI.getAPIKey()).toString();

        Assertions.assertEquals(expected,actual);

    }


    @Test
    @DisplayName("Check ofCitiesInCircle returns correct String")
    void checkOfCitiesInCircle(){
        String expected = "https://api.openweathermap.org/data/2.5/find?lat=1&lon=2&appid=" + WeatherAPI.getAPIKey();
        String actual = URLBuilder.ofCitiesInCircle(1,2, WeatherAPI.getAPIKey()).toString();

        Assertions.assertEquals(expected,actual);
    }


    @Test
    @DisplayName("Check ofZipCode returns correct String")
    void checkOfZipCode(){
        String expected = "https://api.openweathermap.org/data/2.5/weather?zip=82&appid=" + WeatherAPI.getAPIKey();
        String actual = URLBuilder.ofZipCode("82", WeatherAPI.getAPIKey()).toString();


        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Check second ofZipCode returns correct String")
    void checkOfZipCodeTwo(){
        String expected = "https://api.openweathermap.org/data/2.5/weather?zip=82,55&appid=" + WeatherAPI.getAPIKey();
        String actual = URLBuilder.ofZipCode("82", "55",WeatherAPI.getAPIKey()).toString();


        Assertions.assertEquals(expected,actual);
    }

}

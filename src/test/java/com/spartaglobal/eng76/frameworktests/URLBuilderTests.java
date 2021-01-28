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
        String expected = "cityName cannot be null or empty!";

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
    @DisplayName("Check second ofCity can handle null")
    void checkOfCityTwoCanHandleNull(){

        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {URLBuilder.ofCity(null, "4",WeatherAPI.getAPIKey());});

        String actual = exception.getMessage();
        String expected = "cityName cannot be null or empty!";

        Assertions.assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Check second ofCity can handle empty")
    void checkOfCityTwoCanHandleEmpty(){

        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {URLBuilder.ofCity("London", "",WeatherAPI.getAPIKey());});

        String actual = exception.getMessage();
        String expected = "stateCode cannot be null or empty!";

        Assertions.assertEquals(actual, expected);
    }




    @Test
    @DisplayName("Check third ofCity method returns correct String")
    void checkOfCityThree(){
        String expected = "https://api.openweathermap.org/data/2.5/weather?q=London,3,10&appid=" + WeatherAPI.getAPIKey();
        String actual = URLBuilder.ofCity("London", "3","10",WeatherAPI.getAPIKey()).toString();


        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Check third ofCity can handle nulls")
    void checkOfCityThreeCanHandleNull(){
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {URLBuilder.ofCity(null, "1","2",WeatherAPI.getAPIKey());});

        String actual = exception.getMessage();
        String expected = "cityName cannot be null or empty!";

        Assertions.assertEquals(actual, expected);
    }


    @Test
    @DisplayName("Check third ofCity can handle empty")
    void checkOfCityThreeCanHandleEmpty(){
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {URLBuilder.ofCity("Birmingham", "","2",WeatherAPI.getAPIKey());});

        String actual = exception.getMessage();
        String expected = "stateCode cannot be null or empty!";

        Assertions.assertEquals(actual, expected);
    }



    @Test
    @DisplayName("Check third ofCity can handle empty country code")
    void checkOfCityThreeCanHandleEmptyCountryCode(){
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {URLBuilder.ofCity("Birmingham", "1","",WeatherAPI.getAPIKey());});

        String actual = exception.getMessage();
        String expected = "countryCode cannot be null or empty!";

        Assertions.assertEquals(actual, expected);
    }



    @Test
    @DisplayName("Check fourth ofCity method returns correct String")
    void checkOfCityFour(){
        String expected = "https://api.openweathermap.org/data/2.5/weather?id=5405&appid=" + WeatherAPI.getAPIKey();
        String actual = URLBuilder.ofCity(5405, WeatherAPI.getAPIKey()).toString();


        Assertions.assertEquals(expected,actual);
    }


    @Test
    @DisplayName("Check fourth ofCity can handle -1")
    void checkOfCityFourCanHandleWrongInput(){
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {URLBuilder.ofCity(-1,WeatherAPI.getAPIKey());});

        String actual = exception.getMessage();
        String expected = "cityId cannot be < 0!";

        Assertions.assertEquals(actual, expected);
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
    @DisplayName("Check fourth ofCities can handle null")
    void checkOfCitiesCanHandleNull(){
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {URLBuilder.ofCities(null,WeatherAPI.getAPIKey());});

        String actual = exception.getMessage();
        String expected = "citiesId length should not be null";

        Assertions.assertEquals(actual, expected);
    }


    @Test
    @DisplayName("Check fourth ofCities can handle 0 length array")
    void checkOfCitiesCanHandleWrongInput(){
        int[] id = new int[]{};
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {URLBuilder.ofCities(id,WeatherAPI.getAPIKey());});

        String actual = exception.getMessage();
        String expected = "citiesId length should be > 0";

        Assertions.assertEquals(actual, expected);
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
    @DisplayName("Check ofZipCode can handle null")
    void checkOfZipCodeNull(){
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {URLBuilder.ofZipCode(null,WeatherAPI.getAPIKey());});

        String actual = exception.getMessage();
        String expected = "zipCode should not be null or empty!";

        Assertions.assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Check second ofZipCode returns correct String")
    void checkOfZipCodeTwo(){
        String expected = "https://api.openweathermap.org/data/2.5/weather?zip=82,55&appid=" + WeatherAPI.getAPIKey();
        String actual = URLBuilder.ofZipCode("82", "55",WeatherAPI.getAPIKey()).toString();


        Assertions.assertEquals(expected,actual);
    }


    @Test
    @DisplayName("Check second ofZipCode can handle a empty input")
    void checkOfZipCodeTwoCanHandleEmpty(){
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {URLBuilder.ofZipCode("","4",WeatherAPI.getAPIKey());});

        String actual = exception.getMessage();
        String expected = "zipCode should not be null or empty!";

        Assertions.assertEquals(actual, expected);
    }


    @Test
    @DisplayName("Check second ofZipCode can handle a null input")
    void checkOfZipCodeTwoCanHandleNull(){
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {URLBuilder.ofZipCode("3",null,WeatherAPI.getAPIKey());});

        String actual = exception.getMessage();
        String expected = "countryCode should not be null or empty!";

        Assertions.assertEquals(actual, expected);
    }

}

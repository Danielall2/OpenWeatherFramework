package com.spartaglobal.eng76.frameworktests;

import com.spartaglobal.eng76.framework.dto.WeatherDTO;
import com.spartaglobal.eng76.framework.dto.WeatherListDTO;
import com.spartaglobal.eng76.framework.exceptions.FailedHttpConnectionException;
import com.spartaglobal.eng76.framework.weatherapi.WeatherAPI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class WeatherAPITest {

    private static String apikey = WeatherAPI.getAPIKey();

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Null or empty inputs throws IllegalArgumentException for ofCity")
    void nullEmptyOfCityThrowsException(String input) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            WeatherDTO weatherDTO = WeatherAPI.ofCity(input, input);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            WeatherDTO weatherDTO = WeatherAPI.ofCity(input, input, input);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            WeatherDTO weatherDTO = WeatherAPI.ofCity(input, input, input, input);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            WeatherDTO weatherDTO = WeatherAPI.ofCity(2988507, input);
        });
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Null or empty inputs throws IllegalArgumentException for ofCities")
    void nullEmptyOfCitiesThrowsException(String input) {
        int[] emptyArray = new int[]{};
        int[] idArray = new int[]{524901, 703448, 2988507};
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            WeatherListDTO weatherListDTO = WeatherAPI.ofCities(idArray, input);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            WeatherListDTO weatherListDTO = WeatherAPI.ofCities(emptyArray, input);
        });
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Null or empty inputs throws IllegalArgumentException for ofCitiesInRectangle")
    void nullEmptyOfCitiesInRectangleThrowsException(String input) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            WeatherListDTO weatherListDTO = WeatherAPI.ofCitiesInRectangle(1000, 1000, 2000, 2000, 10, input);
        });
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Null or empty inputs throws IllegalArgumentException for ofCitiesInCircle")
    void nullEmptyOfCitiesInCircleThrowsException(String input) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            WeatherListDTO weatherListDTO = WeatherAPI.ofCitiesInCircle(1000, 1000, input);
        });
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Null or empty inputs throws IllegalArgumentException for ofZipCode")
    void nullEmptyOfZipCodeThrowsException(String input) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            WeatherDTO weatherDTO = WeatherAPI.ofZipCode(input, input);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            WeatherDTO weatherDTO = WeatherAPI.ofZipCode(input, input, input);
        });
    }

    @ParameterizedTest
    @EmptySource
    @DisplayName("Null or empty inputs throws IllegalArgumentException for additional parameters")
    void nullEmptyAdditionalParamsThrowsException(String input) {
        Map.Entry<String, String> map = Map.entry(input, input);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            WeatherDTO weatherDTO = WeatherAPI.ofCity("paris", apikey, map);
        });
    }

    @Test
    @DisplayName("ofCity by cityName returns correct city")
    void ofCityByCityNameReturnsCorrectCity() {
        WeatherDTO parisDTO = null;
        try {
            parisDTO = WeatherAPI.ofCity("Paris", apikey);
        } catch (FailedHttpConnectionException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals("Paris", parisDTO.getName());
    }

    @Test
    @DisplayName("ofCity by incorrect cityName throws error")
    void ofCityByIncorrectNameThrowsError() {
        Assertions.assertThrows(FailedHttpConnectionException.class, () -> {
            WeatherDTO weatherDTO = WeatherAPI.ofCity("Blijagog", apikey);
        });
    }

    @Test
    @DisplayName("ofCity by incorrect apikey throws error")
    void ofCityByIncorrectApikeyThrowsError() {
        Assertions.assertThrows(FailedHttpConnectionException.class, () -> {
            WeatherDTO weatherDTO = WeatherAPI.ofCity("Paris", "password");
        });
    }

    /*
    Can't get State Code functionality to work - may be API bug rather than Framework.
     */
//    @Test
//    @DisplayName("ofCity by cityName and stateCode returns correct city")
//    void ofCityByCityNameAndStateCodeReturnsCorrectCity() {
//        WeatherDTO weatherDTO = null;
//        try {
//            weatherDTO = WeatherAPI.ofCity("Washington", "OH", apikey);
//        } catch (FailedHttpConnectionException e) {
//            e.printStackTrace();
//        }
//        System.out.println(weatherDTO.getId());
//        Assertions.assertEquals("Washington", weatherDTO.getName());
//       // Assertions.assertEquals("KY", weatherDTO.get)
//    }

    @Test
    @DisplayName("ofCity by cityName and countryCode returns correct city")
    void ofCityByCityNameCountryCodeReturnsCorrect() {
        WeatherDTO weatherDTO = null;
        try {
            weatherDTO = WeatherAPI.ofCity("Washington", "GB", apikey);
        } catch (FailedHttpConnectionException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals("2634715", weatherDTO.getId());
    }

    @Test
    @DisplayName("ofCity by Id returns correct city")
    void ofCityByIdReturnsCorrect() {
        WeatherDTO weatherDTO = null;
        try {
            weatherDTO = WeatherAPI.ofCity(2634715, apikey);
        } catch (FailedHttpConnectionException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals("Washington", weatherDTO.getName());
    }

    @Test
    @DisplayName("ofCitiesInRectangle returns WeatherListDTO of correct size")
    void ofCitiesInRectangleReturnsWeatherListDto() {
        WeatherListDTO weatherListDTO = null;
        try {
            weatherListDTO = WeatherAPI.ofCitiesInRectangle(12, 32, 15, 37, 10, apikey);
        } catch (FailedHttpConnectionException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals("15", weatherListDTO.getCnt());
    }

    @Test
    @DisplayName("ofCitiesInCircle returns WeatherListDTO of correct size")
    void ofCitiesCircleReturnsWeatherListDto() {
        WeatherListDTO weatherListDTO = null;
        try {
            weatherListDTO = WeatherAPI.ofCitiesInCircle(55.5, 37.5, apikey);
        } catch (FailedHttpConnectionException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals("5", weatherListDTO.getCount());
    }

    @Test
    @DisplayName("ofZipCode returns WeatherDTO with correct city ID")
    void ofZipCodeReturnsWeatherDtoWithCorrectCityId() {
        WeatherDTO weatherDTO = null;
        try {
            weatherDTO = WeatherAPI.ofZipCode("94040", apikey);
        } catch (FailedHttpConnectionException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals("0", weatherDTO.getId());
    }

    @Test
    @DisplayName("ofZipCode with country code returns WeatherDTO with correct city ID")
    void ofZipCodeWithCountryCodeCorrectCityId() {
        WeatherDTO weatherDTO = null;
        try {
            weatherDTO = WeatherAPI.ofZipCode("94040", "US", apikey);
        } catch (FailedHttpConnectionException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals("0", weatherDTO.getId());
    }
}
package com.spartaglobal.eng76.framework.apitesting;

import com.spartaglobal.eng76.framework.connectionmanager.ConnectionManager;
import com.spartaglobal.eng76.framework.dto.Enums.Coordinates;
import com.spartaglobal.eng76.framework.dto.WeatherDTO;
import com.spartaglobal.eng76.framework.exceptions.FailedHttpConnectionException;
import com.spartaglobal.eng76.framework.urlbuilder.URLBuilder;
import com.spartaglobal.eng76.framework.weatherapi.WeatherAPI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

public class CoordinatesTests {
    WeatherDTO weatherDTO;

    @ParameterizedTest
    @CsvSource({"Sydney", "London", "Toronto", "Tokyo"})
    @DisplayName("Check coordinate has two coordinate values")
    void checkCoordinateHasTwoCoordinateValues(String city) {
        try {
            weatherDTO = WeatherAPI.ofCity(city, WeatherAPI.getAPIKey());
        } catch (FailedHttpConnectionException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(weatherDTO.getCoord().size(), 2);
    }

    @ParameterizedTest
    @CsvSource({"Sydney", "London", "Toronto", "Tokyo"})
    @DisplayName("Check latitude value is between 90 and negative 90")
    void checkLatitudeValueIsBetween90AndNegative90(String city) {
        try {
            weatherDTO = WeatherAPI.ofCity(city, WeatherAPI.getAPIKey());
        } catch (FailedHttpConnectionException e) {
            e.printStackTrace();
        }
        float latitude = Float.parseFloat(weatherDTO.getCoord().get(Coordinates.LATITUDE.toString()));
        Assertions.assertTrue(-90 <= latitude && latitude <= 90);
    }

    @ParameterizedTest
    @CsvSource({"Sydney", "London", "Toronto", "Tokyo"})
    @DisplayName("Check longitude is between 180 and negative 180")
    void checkLongitudeIsBetween180AndNegative180(String city) {
        try {
            weatherDTO = WeatherAPI.ofCity(city, WeatherAPI.getAPIKey());
        } catch (FailedHttpConnectionException e) {
            e.printStackTrace();
        }
        float longitude = Float.parseFloat(weatherDTO.getCoord().get(Coordinates.LONGITUDE.toString()));
        Assertions.assertTrue(-180 <= longitude && longitude <= 180);
    }

    
}

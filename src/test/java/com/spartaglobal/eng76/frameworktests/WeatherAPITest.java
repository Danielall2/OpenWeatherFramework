package com.spartaglobal.eng76.frameworktests;

import com.spartaglobal.eng76.framework.dto.WeatherDTO;
import com.spartaglobal.eng76.framework.weatherapi.WeatherAPI;
import org.junit.jupiter.api.BeforeAll;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class WeatherAPITest {

    private static WeatherDTO weatherDTO;
    private static String apikey;
    private static int cityId = 2172797;

    @BeforeAll
    static void setup(){
        apikey = WeatherAPI.getAPIKey();
        weatherDTO = WeatherAPI.ofCity(cityId, apikey);
    }
}
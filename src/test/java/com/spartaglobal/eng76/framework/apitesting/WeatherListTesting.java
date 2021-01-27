package com.spartaglobal.eng76.framework.apitesting;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class WeatherListTesting {
    HashMap<Integer, String> weatherConditions = new HashMap<>(55);

    public HashMap<Integer, String> getWeatherConditions() {
        return weatherConditions;
    }

    public String[] getWeatherConditions(int weatherID) {
        return weatherConditions.get(weatherID).split(",");
    }

    public String getWeatherMain() {
        return null;
    }

    void addWeatherConditions() {
        addWeatherConditionsThunderstorm();
    }

    void addWeatherConditionsThunderstorm() {
        weatherConditions.put(200, "Thunderstorm, thunderstorm with light rain, 11d");
        weatherConditions.put(201, "Thunderstorm, thunderstorm with rain, 11d");
        weatherConditions.put(202, "Thunderstorm, thunderstorm with heavy rain, 11d");
        weatherConditions.put(210, "Thunderstorm, light thunderstorm, 11d");
        weatherConditions.put(211, "Thunderstorm, thunderstorm, 11d");
        weatherConditions.put(212, "Thunderstorm, heavy thunderstorm, 11d");
        weatherConditions.put(221, "Thunderstorm, ragged thunderstorm, 11d");
        weatherConditions.put(230, "Thunderstorm, thunderstorm with light rain, 11d");
        weatherConditions.put(231, "Thunderstorm, thunderstorm with light drizzle, 11d");
        weatherConditions.put(232, "Thunderstorm, thunderstorm with heavy drizzle, 11d");
    }

    @Test
    void isWeatherAddedToHashMap() {
        addWeatherConditions();
        System.out.println(weatherConditions.toString());
    }


}

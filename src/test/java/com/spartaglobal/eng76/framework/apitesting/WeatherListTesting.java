package com.spartaglobal.eng76.framework.apitesting;

import com.spartaglobal.eng76.framework.dto.WeatherDTO;
import com.spartaglobal.eng76.framework.weatherapi.WeatherAPI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;

public class WeatherListTesting {
    static HashMap<Integer, String> weatherConditions = new HashMap<>(55);

    public String[] getWeatherConditions(int weatherID) {
        return weatherConditions.get(weatherID).split(",");
    }

    public String getWeatherMain(int weatherID) {
        return getWeatherConditions(weatherID)[0].trim();
    }

    public String getWeatherDescription(int weatherID) {
        return getWeatherConditions(weatherID)[1].trim();
    }

    public String getWeatherIconID(int weatherID) {
        return getWeatherConditions(weatherID)[2].trim();
    }

    private static void addWeatherConditions() {
        addWeatherConditionsThunderstorm();
        addWeatherConditionsDrizzle();
        addWeatherConditionsRain();
        addWeatherConditionsSnow();
        addWeatherConditionsAtmosphere();
        addWeatherConditionsClouds();
    }

    private static void addWeatherConditionsThunderstorm() {
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

    private static void addWeatherConditionsDrizzle() {
        weatherConditions.put(300, "Drizzle, light intensity drizzle, 09d");
        weatherConditions.put(301, "Drizzle, drizzle, 09d");
        weatherConditions.put(302, "Drizzle, heavy intensity drizzle, 09d");
        weatherConditions.put(310, "Drizzle, light intensity drizzle rain, 09d");
        weatherConditions.put(311, "Drizzle, drizzle rain, 09d");
        weatherConditions.put(312, "Drizzle, heavy intensity drizzle rain, 09d");
        weatherConditions.put(313, "Drizzle, shower rain and drizzle, 09d");
        weatherConditions.put(314, "Drizzle, heavy shower rain and drizzle, 09d");
        weatherConditions.put(321, "Drizzle, shower drizzle, 09d");
    }

    private static void addWeatherConditionsRain() {
        weatherConditions.put(500, "Rain, light rain, 10d");
        weatherConditions.put(501, "Rain, moderate rain, 10d");
        weatherConditions.put(502, "Rain, heavy intensity rain, 10d");
        weatherConditions.put(503, "Rain, very heavy rain, 10d");
        weatherConditions.put(504, "Rain, extreme rain, 10d");
        weatherConditions.put(511, "Rain, freezing rain, 13d");
        weatherConditions.put(520, "Rain, light intensity shower rain, 09d");
        weatherConditions.put(521, "Rain, shower rain, 09d");
        weatherConditions.put(522, "Rain, heavy intensity shower rain, 09d");
        weatherConditions.put(531, "Rain, ragged shower rain, 10d");
    }

    private static void addWeatherConditionsSnow() {
        weatherConditions.put(600, "Snow, light snow, 13d");
        weatherConditions.put(601, "Snow, Snow, 13d");
        weatherConditions.put(602, "Snow, Heavy snow, 13d");
        weatherConditions.put(611, "Snow, Sleet, 13d");
        weatherConditions.put(612, "Snow, Light shower sleet, 13d");
        weatherConditions.put(613, "Snow, Shower sleet, 13d");
        weatherConditions.put(615, "Snow, Light rain and snow, 13d");
        weatherConditions.put(616, "Snow, Rain and snow, 13d");
        weatherConditions.put(620, "Snow, Light shower snow, 13d");
        weatherConditions.put(621, "Snow, Shower snow, 13d");
        weatherConditions.put(622, "Snow, Heavy shower snow, 13d");
    }

    private static void addWeatherConditionsAtmosphere() {
        weatherConditions.put(701, "Mist, mist, 50d");
        weatherConditions.put(711, "Smoke, smoke, 50d");
        weatherConditions.put(721, "Haze, Haze, 50d");
        weatherConditions.put(731, "Dust, sand/ dust whirls, 50d");
        weatherConditions.put(741, "Fog, fog, 50d");
        weatherConditions.put(751, "Sand, sand, 50d");
        weatherConditions.put(761, "Dust, dust, 50d");
        weatherConditions.put(762, "Ash, volcanic ash, 50d");
        weatherConditions.put(771, "Squall, squalls, 50d");
        weatherConditions.put(781, "Tornado, tornado, 50d");
    }

    private static void addWeatherConditionsClouds() {
        weatherConditions.put(800, "Clear, clear sky, 01d 01n");
        weatherConditions.put(801, "Clouds, few clouds: 11-25%, 02d 02n");
        weatherConditions.put(802, "Clouds, scattered clouds: 25-50%, 03d 03n");
        weatherConditions.put(803, "Clouds, broken clouds: 51-84%, 04d 04n");
        weatherConditions.put(804, "Clouds, overcast clouds: 85-100%, 04d 04n");
    }

    @BeforeAll
    static void setup() {
        addWeatherConditions();
    }

    @Test
    void isWeatherAddedToHashMap() {
        System.out.println(weatherConditions.toString());
    }

    @ParameterizedTest
    @ValueSource(ints = {200, 300, 500, 600, 701, 800})
    @DisplayName("Does the weather ID return a valid 'main' from the HashMap?")
    void isWeatherMainReturnedFromHashMap(int weatherID) {
        if(weatherConditions.containsKey(weatherID)) {
            System.out.println(getWeatherMain(weatherID));
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {200, 300, 500, 600, 701, 800})
    @DisplayName("Does the weather ID return a valid 'description' from the HashMap?")
    void isWeatherDescriptionReturnedFromHashMap(int weatherID) {
        if(weatherConditions.containsKey(weatherID)) {
            System.out.println(getWeatherDescription(weatherID));
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {200, 300, 500, 600, 701, 800})
    @DisplayName("Does the weather ID return a valid 'icon id' from the HashMap?")
    void isWeatherIconIDReturnedFromHashMap(int weatherID) {
        if(weatherConditions.containsKey(weatherID)) {
            System.out.println(getWeatherIconID(weatherID));
        }
    }

    void isResponseWeatherValid()  {

    }

}

package com.spartaglobal.eng76.framework.apitesting;

import com.spartaglobal.eng76.framework.dto.Enums.Weather;
import com.spartaglobal.eng76.framework.weatherapi.WeatherAPI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class WeatherListTesting {
    static HashMap<String, String>  weatherConditions   = new HashMap<>(55);
    static Properties               properties          = new Properties();

    /**
     * With given weatherID, get the string from HashMap and split it by ',' so Main,
     * Description and Icon ID can be retrieved individually from String array
     * @param weatherID ID number for specific weather condition
     * @return Main, Description and Icon ID in String array
     */
    public String[] getWeatherConditions(String weatherID) {
        return weatherConditions.get(weatherID).split(",");
    }

    /**
     * From getWeatherConditions, take the first item in the array, which is
     * always Main weather category
     * @param weatherID ID number for specific weather condition
     * @return Main weather category
     */
    public String getWeatherMain(String weatherID) {
        return getWeatherConditions(weatherID)[0].trim();
    }

    /**
     * From getWeatherConditions, take the second item in the array, which is
     * always weather Description
     * @param weatherID ID number for specific weather condition
     * @return Description of weather
     */
    public String getWeatherDescription(String weatherID) {
        return getWeatherConditions(weatherID)[1].trim();
    }

    /**
     * From getWeatherConditions, take the third item in the array, which is
     * always weather Icon ID
     * @param weatherID ID number for specific weather condition
     * @return Icon ID of weather
     */
    public String getWeatherIconID(String weatherID) {
        return getWeatherConditions(weatherID)[2].trim();
    }

    /**
     * Add all weather conditions from https://openweathermap.org/weather-conditions to a HashMap.
     * The HashMap is a local version of the tabled data that can be asserted against by the response(s)
     * from the live API
     */
    private static void addWeatherConditions() {
        addWeatherConditionsThunderstorm();
        addWeatherConditionsDrizzle();
        addWeatherConditionsRain();
        addWeatherConditionsSnow();
        addWeatherConditionsAtmosphere();
        addWeatherConditionsClouds();
    }

    /**
     * Add all Thunderstorm weather conditions from https://openweathermap.org/weather-conditions
     * to a HashMap
     */
    private static void addWeatherConditionsThunderstorm() {
        weatherConditions.put("200", "Thunderstorm, thunderstorm with light rain, 11d");
        weatherConditions.put("201", "Thunderstorm, thunderstorm with rain, 11d");
        weatherConditions.put("202", "Thunderstorm, thunderstorm with heavy rain, 11d");
        weatherConditions.put("210", "Thunderstorm, light thunderstorm, 11d");
        weatherConditions.put("211", "Thunderstorm, thunderstorm, 11d");
        weatherConditions.put("212", "Thunderstorm, heavy thunderstorm, 11d");
        weatherConditions.put("221", "Thunderstorm, ragged thunderstorm, 11d");
        weatherConditions.put("230", "Thunderstorm, thunderstorm with light rain, 11d");
        weatherConditions.put("231", "Thunderstorm, thunderstorm with light drizzle, 11d");
        weatherConditions.put("232", "Thunderstorm, thunderstorm with heavy drizzle, 11d");
    }

    /**
     * Add all Drizzle weather conditions from https://openweathermap.org/weather-conditions
     * to a HashMap
     */
    private static void addWeatherConditionsDrizzle() {
        weatherConditions.put("300", "Drizzle, light intensity drizzle, 09d");
        weatherConditions.put("301", "Drizzle, drizzle, 09d");
        weatherConditions.put("302", "Drizzle, heavy intensity drizzle, 09d");
        weatherConditions.put("310", "Drizzle, light intensity drizzle rain, 09d");
        weatherConditions.put("311", "Drizzle, drizzle rain, 09d");
        weatherConditions.put("312", "Drizzle, heavy intensity drizzle rain, 09d");
        weatherConditions.put("313", "Drizzle, shower rain and drizzle, 09d");
        weatherConditions.put("314", "Drizzle, heavy shower rain and drizzle, 09d");
        weatherConditions.put("321", "Drizzle, shower drizzle, 09d");
    }

    /**
     * Add all Rain weather conditions from https://openweathermap.org/weather-conditions
     * to a HashMap
     */
    private static void addWeatherConditionsRain() {
        weatherConditions.put("500", "Rain, light rain, 10d");
        weatherConditions.put("501", "Rain, moderate rain, 10d");
        weatherConditions.put("502", "Rain, heavy intensity rain, 10d");
        weatherConditions.put("503", "Rain, very heavy rain, 10d");
        weatherConditions.put("504", "Rain, extreme rain, 10d");
        weatherConditions.put("511", "Rain, freezing rain, 13d");
        weatherConditions.put("520", "Rain, light intensity shower rain, 09d");
        weatherConditions.put("521", "Rain, shower rain, 09d");
        weatherConditions.put("522", "Rain, heavy intensity shower rain, 09d");
        weatherConditions.put("531", "Rain, ragged shower rain, 10d");
    }

    /**
     * Add all Snow weather conditions from https://openweathermap.org/weather-conditions
     * to a HashMap
     */
    private static void addWeatherConditionsSnow() {
        weatherConditions.put("600", "Snow, light snow, 13d");
        weatherConditions.put("601", "Snow, Snow, 13d");
        weatherConditions.put("602", "Snow, Heavy snow, 13d");
        weatherConditions.put("611", "Snow, Sleet, 13d");
        weatherConditions.put("612", "Snow, Light shower sleet, 13d");
        weatherConditions.put("613", "Snow, Shower sleet, 13d");
        weatherConditions.put("615", "Snow, Light rain and snow, 13d");
        weatherConditions.put("616", "Snow, Rain and snow, 13d");
        weatherConditions.put("620", "Snow, Light shower snow, 13d");
        weatherConditions.put("621", "Snow, Shower snow, 13d");
        weatherConditions.put("622", "Snow, Heavy shower snow, 13d");
    }

    /**
     * Add all Atmosphere weather conditions from https://openweathermap.org/weather-conditions
     * to a HashMap
     */
    private static void addWeatherConditionsAtmosphere() {
        weatherConditions.put("701", "Mist, mist, 50d");
        weatherConditions.put("711", "Smoke, smoke, 50d");
        weatherConditions.put("721", "Haze, Haze, 50d");
        weatherConditions.put("731", "Dust, sand/ dust whirls, 50d");
        weatherConditions.put("741", "Fog, fog, 50d");
        weatherConditions.put("751", "Sand, sand, 50d");
        weatherConditions.put("761", "Dust, dust, 50d");
        weatherConditions.put("762", "Ash, volcanic ash, 50d");
        weatherConditions.put("771", "Squall, squalls, 50d");
        weatherConditions.put("781", "Tornado, tornado, 50d");
    }

    /**
     * Add all Clear/Clouds weather conditions from https://openweathermap.org/weather-conditions
     * to a HashMap
     */
    private static void addWeatherConditionsClouds() {
        weatherConditions.put("800", "Clear, clear sky, 01d 01n");
        weatherConditions.put("801", "Clouds, few clouds, 02d 02n");
        weatherConditions.put("802", "Clouds, scattered clouds, 03d 03n");
        weatherConditions.put("803", "Clouds, broken clouds, 04d 04n");
        weatherConditions.put("804", "Clouds, overcast clouds, 04d 04n");
    }

    @BeforeAll
    static void setup() {
        addWeatherConditions();

        try {
            properties.load(new FileReader("src/test/resources/apikey.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void isWeatherAddedToHashMap() {
        System.out.println(weatherConditions.toString());
    }

    @ParameterizedTest
    @ValueSource(strings = {"200","300", "500", "600", "701", "800", "100", "1000"})
    @DisplayName("Does the weather ID return a valid 'main' from the HashMap?")
    void isWeatherMainReturnedFromHashMap(String weatherID) {
        System.out.println("Weather ID:: " + weatherID);
        if(weatherConditions.containsKey(weatherID)) {
            System.out.println("Weather main category:: " + getWeatherMain(weatherID));
        } else {
            System.out.println("Weather ID not found in local data source");
        }

        Assertions.assertTrue(weatherConditions.containsKey(weatherID));
    }

    @ParameterizedTest
    @ValueSource(strings = {"200", "300", "500", "600", "701", "800", "100", "1000"})
    @DisplayName("Does the weather ID return a valid 'description' from the HashMap?")
    void isWeatherDescriptionReturnedFromHashMap(String weatherID) {
        System.out.println("Weather ID:: " + weatherID);
        if(weatherConditions.containsKey(weatherID)) {
            System.out.println("Weather description:: " + getWeatherDescription(weatherID));
        } else {
            System.out.println("Weather ID not found in local data source");
        }

        Assertions.assertTrue(weatherConditions.containsKey(weatherID));
    }

    @ParameterizedTest
    @ValueSource(strings = {"200", "300", "500", "600", "701", "800", "100", "1000"})
    @DisplayName("Does the weather ID return a valid 'icon id' from the HashMap?")
    void isWeatherIconIDReturnedFromHashMap(String weatherID) {
        System.out.println("Weather ID:: " + weatherID);
        if(weatherConditions.containsKey(weatherID)) {
            System.out.println("Weather icon ID:: " + getWeatherIconID(weatherID));
        } else {
            System.out.println("Weather ID not found in local data source");
        }

        Assertions.assertTrue(weatherConditions.containsKey(weatherID));
    }

    @ParameterizedTest
    @CsvSource({"leeds" ,
                "berlin",
                "lasvegas",
                "johannesburg",
                "brasilia"})
    @DisplayName("Does the current city have at least one weather condition?")
    void isWeatherArrayEmpty(String city) {
        List<HashMap<String, String>> currentWeather = WeatherAPI.ofCity(city, properties.getProperty("apikey")).getWeather();
        System.out.println(city + " currently has " + currentWeather.size() + " weather condition(s)");
        Assertions.assertTrue(currentWeather.size() > 0);
    }

    @ParameterizedTest
    @CsvSource({"leeds" ,
                "berlin",
                "lasvegas",
                "johannesburg",
                "brasilia"})
    @DisplayName("Does the current weather condition(s) have a valid ID?")
    void isResponseWeatherIDValid(String city)  {
        for(HashMap<String, String> currentWeather:WeatherAPI.ofCity(city, properties.getProperty("apikey")).getWeather()) {
            System.out.println("Current weather ID ::" + currentWeather.get(Weather.ID.toString()));
            Assertions.assertTrue(weatherConditions.containsKey(currentWeather.get(Weather.ID.toString())));
        }
    }

    @ParameterizedTest
    @CsvSource({"leeds" ,
                "berlin",
                "lasvegas",
                "johannesburg",
                "brasilia"})
    @DisplayName("Does the current weather condition(s) have a valid Main category?")
    void isResponseWeatherMainValid(String city) {
        for(HashMap<String, String> currentWeather:WeatherAPI.ofCity(city, properties.getProperty("apikey")).getWeather()) {
            System.out.println("Current weather ID ::" + currentWeather.get(Weather.ID.toString()));
            System.out.println("Current weather Main category ::" + currentWeather.get(Weather.MAIN.toString()));
            Assertions.assertEquals(currentWeather.get(Weather.MAIN.toString()), getWeatherMain(currentWeather.get(Weather.ID.toString())));
        }
    }

    @ParameterizedTest
    @CsvSource({"leeds" ,
                "berlin",
                "lasvegas",
                "johannesburg",
                "brasilia"})
    @DisplayName("Does the current weather condition(s) have a valid Description?")
    void isResponseWeatherDescriptionValid(String city) {
        for(HashMap<String, String> currentWeather:WeatherAPI.ofCity(city, properties.getProperty("apikey")).getWeather()) {
            System.out.println("Current weather ID ::" + currentWeather.get(Weather.ID.toString()));
            System.out.println("Current weather Description ::" + currentWeather.get(Weather.DESCRIPTION.toString()));
            Assertions.assertEquals(currentWeather.get(Weather.DESCRIPTION.toString()), getWeatherDescription(currentWeather.get(Weather.ID.toString())));
        }
    }

    @ParameterizedTest
    @CsvSource({"leeds" ,
                "berlin",
                "lasvegas",
                "johannesburg",
                "brasilia"})
    @DisplayName("Does the current weather condition(s) have a valid Icon ID?")
    void isResponseWeatherIconValid(String city) {
        for(HashMap<String, String> currentWeather:WeatherAPI.ofCity(city, properties.getProperty("apikey")).getWeather()) {
            if(currentWeather.get(Weather.MAIN.toString()).equals("Clear") || currentWeather.get(Weather.MAIN.toString()).equals("Clouds")) {
                System.out.println("Current weather ID ::" + currentWeather.get(Weather.ID.toString()));
                System.out.println("Current weather Icon ID ::" + currentWeather.get(Weather.ICON.toString()));
                Assertions.assertTrue(getWeatherIconID(currentWeather.get(Weather.ID.toString())).contains(currentWeather.get(Weather.ICON.toString())));
            } else {
                System.out.println("Current weather ID ::" + currentWeather.get(Weather.ID.toString()));
                System.out.println("Current weather Icon ID ::" + currentWeather.get(Weather.ICON.toString()));
                Assertions.assertEquals(getWeatherIconID(currentWeather.get(Weather.ID.toString())), currentWeather.get(Weather.ICON.toString()));
            }
        }
    }

}
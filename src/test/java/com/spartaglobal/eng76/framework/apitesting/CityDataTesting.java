package com.spartaglobal.eng76.framework.apitesting;

import com.spartaglobal.eng76.framework.dto.Enums.Sys;
import com.spartaglobal.eng76.framework.weatherapi.WeatherAPI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class CityDataTesting {

    static HashMap<String, String> cityData   = new HashMap<>(10);
    static Properties              properties = new Properties();

    /**
     * Using "sys":"id" get city data in the format: "name", "country", "timezone"
     * @param cityID ID of city (API-specific)
     * @return Name, Country and Timezone in String array
     */
    public String[] getCityData(String cityID) {
        return cityData.get(cityID).split(",");
    }

    /**
     * get city "name" from "sys":"id"
     * @param cityID ID of city (API-specific)
     * @return the name of the argument city
     */
    public String getCityName(String cityID) {
        return getCityData(cityID)[0].trim();
    }

    public String getCityCountry(String cityID) {
        return getCityData(cityID)[1].trim();
    }

    public String getCityTimezone(String cityID) {
        return getCityData(cityID)[2].trim();
    }

    /**
     * Add individual city data to test against in the format:
     * "id", "name", "sys":"country", "timezone".
     */
    private static void addCityData() {
        cityData.put("1423", "leeds, GB, 0");
        cityData.put("1262", "berlin, DE, 3600");
        cityData.put("7113", "lasvegas, MX, -21600");
        cityData.put("2007136", "johannesburg, ZA, 7200");
        cityData.put("8336", "brasilia, BR, -10800");
        cityData.put("9600", "sydney, AU, 39600");
        cityData.put("2003771", "bangkok, TH, 25200");
        cityData.put("2514", "cairo, EG, 7200");
        cityData.put("322", "quebec, CA, -18000");
        cityData.put("7878", "honolulu, US, -36000");
    }

    @BeforeAll
    static void setup() {
        addCityData();

        try {
            properties.load(new FileReader("src/test/resources/apikey.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"1423", "1262", "7113",
                            "2007136", "8336", "9600",
                            "2003771", "2514", "322", "7878"})
    @DisplayName("Does the city ID return a valid 'name' from the HashMap?")
    void isCityNameReturnedFromHashMap(String cityID) {
        System.out.println("City ID:: " + cityID);
        if(cityData.containsKey(cityID)) {
            System.out.println("City name:: " + getCityName(cityID));
        } else {
            System.out.println("City not found with ID:: " + cityID);
        }

        Assertions.assertTrue(cityData.containsKey(cityID));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1423", "1262", "7113",
                            "2007136", "8336", "9600",
                            "2003771", "2514", "322", "7878"})
    @DisplayName("Does the city ID return a valid 'country' from the HashMap?")
    void isCityCountryReturnedFromArrayList(String cityID) {
        System.out.println("City ID:: " + cityID);
        if(cityData.containsKey(cityID)) {
            System.out.println("City country:: " + getCityCountry(cityID));
        } else {
            System.out.println("City not found with ID:: " + cityID);
        }

        Assertions.assertTrue(cityData.containsKey(cityID));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1423", "1262", "7113",
                            "2007136", "8336", "9600",
                            "2003771", "2514", "322", "7878"})
    @DisplayName("Does the city ID return a valid 'timezone' from the HashMap?")
    void isCityTimezoneReturnedFromArrayList(String cityID) {
        System.out.println("City ID:: " + cityID);
        if(cityData.containsKey(cityID)) {
            System.out.println("City timezone:: " + getCityTimezone(cityID) + " seconds leading/trailing GMT");
        } else {
            System.out.println("City not found with ID:: " + cityID);
        }

        Assertions.assertTrue(cityData.containsKey(cityID));
    }

    @ParameterizedTest
    @CsvSource({"leeds", "berlin",
                "lasvegas", "johannesburg",
                "brasilia", "sydney",
                "bangkok", "cairo",
                "quebec", "honolulu"})
    @DisplayName("Does the current city have a valid ID?")
    void isResponseCityIDValid(String cityName) {
        String cityID = WeatherAPI.ofCity(cityName, properties.getProperty("apikey")).getSys().get(Sys.ID.toString());
        System.out.println("City ID from response:: " + cityID);

        Assertions.assertTrue(cityData.containsKey(cityID));
    }

    @ParameterizedTest
    @CsvSource({"leeds", "berlin",
                "lasvegas", "johannesburg",
                "brasilia", "sydney",
                "bangkok", "cairo",
                "quebec", "honolulu"})
    @DisplayName("Does the current city have a valid name?")
    void isResponseCityNameValid(String cityNameLocal) {
        String cityID = WeatherAPI.ofCity(cityNameLocal, properties.getProperty("apikey")).getSys().get(Sys.ID.toString());
        String cityNameAPI = WeatherAPI.ofCity(cityNameLocal, properties.getProperty("apikey")).getName().toLowerCase();
        System.out.println("City name from response:: " + cityNameAPI);

        Assertions.assertEquals(getCityName(cityID), cityNameAPI);
    }

    @ParameterizedTest
    @CsvSource({"leeds", "berlin",
                "lasvegas", "johannesburg",
                "brasilia", "sydney",
                "bangkok", "cairo",
                "quebec", "honolulu"})
    @DisplayName("Does the current city have a valid country code?")
    void isResponseCityCountryValid(String cityName) {
        String cityID = WeatherAPI.ofCity(cityName, properties.getProperty("apikey")).getSys().get(Sys.ID.toString());
        String cityCountry = WeatherAPI.ofCity(cityName, properties.getProperty("apikey")).getSys().get(Sys.COUNTRY.toString());
        System.out.println("City country from response:: " + cityCountry);

        Assertions.assertEquals(getCityCountry(cityID), cityCountry);
    }

    @ParameterizedTest
    @CsvSource({"leeds", "berlin",
                "lasvegas", "johannesburg",
                "brasilia", "sydney",
                "bangkok", "cairo",
                "quebec", "honolulu"})
    @DisplayName("Does the current city have a valid timezone?")
    void isResponseCityTimezoneValid(String cityName) {
        String cityID = WeatherAPI.ofCity(cityName, properties.getProperty("apikey")).getSys().get(Sys.ID.toString());
        String cityTimezone = WeatherAPI.ofCity(cityName, properties.getProperty("apikey")).getTimezone();
        System.out.println("City timezone from response:: " + cityTimezone);

        Assertions.assertEquals(getCityTimezone(cityID), cityTimezone);
    }
}

package com.spartaglobal.eng76.framework.apitesting;

import com.spartaglobal.eng76.framework.dto.Enums.Sys;
import com.spartaglobal.eng76.framework.exceptions.FailedHttpConnectionException;
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
        cityData.put("3333164", "leeds, GB, 0");
        cityData.put("2950159", "berlin, DE, 3600");
        cityData.put("4011743", "lasvegas, MX, -21600");
        cityData.put("993800", "johannesburg, ZA, 7200");
        cityData.put("3469058", "brasilia, BR, -10800");
        cityData.put("2147714", "sydney, AU, 39600");
        cityData.put("1609350", "bangkok, TH, 25200");
        cityData.put("360630", "cairo, EG, 7200");
        cityData.put("6325494", "quebec, CA, -18000");
        cityData.put("5856195", "honolulu, US, -36000");
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
    @ValueSource(strings = {"3333164", "2950159", "4011743",
                            "993800", "3469058", "2147714",
                            "1609350", "360630", "6325494", "5856195"})
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
    @ValueSource(strings = {"3333164", "2950159", "4011743",
                            "993800", "3469058", "2147714",
                            "1609350", "360630", "6325494", "5856195"})
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
    @ValueSource(strings = {"3333164", "2950159", "4011743",
                            "993800", "3469058", "2147714",
                            "1609350", "360630", "6325494", "5856195"})
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
        String cityID = null;
        try {
            cityID = WeatherAPI.ofCity(cityName, WeatherAPI.getAPIKey()).getId();
            System.out.println("City ID from response:: " + cityID);

            Assertions.assertTrue(cityData.containsKey(cityID));
        } catch (FailedHttpConnectionException e) {
            e.printStackTrace();
        };
    }

    @ParameterizedTest
    @CsvSource({"leeds", "berlin",
                "lasvegas", "johannesburg",
                "brasilia", "sydney",
                "bangkok", "cairo",
                "quebec", "honolulu"})
    @DisplayName("Does the current city have a valid name?")
    void isResponseCityNameValid(String cityNameLocal) {
        String cityID = null;
        try {
            cityID = WeatherAPI.ofCity(cityNameLocal, WeatherAPI.getAPIKey()).getId();
            String cityNameAPI = WeatherAPI.ofCity(cityNameLocal, properties.getProperty("apikey")).getName().toLowerCase();
            System.out.println("City name from response:: " + cityNameAPI);

            Assertions.assertEquals(getCityName(cityID), cityNameAPI);
        } catch (FailedHttpConnectionException e) {
            e.printStackTrace();
        };
    }

    @ParameterizedTest
    @CsvSource({"leeds", "berlin",
                "lasvegas", "johannesburg",
                "brasilia", "sydney",
                "bangkok", "cairo",
                "quebec", "honolulu"})
    @DisplayName("Does the current city have a valid country code?")
    void isResponseCityCountryValid(String cityName) {
        String cityID = null;
        try {
            cityID = WeatherAPI.ofCity(cityName, WeatherAPI.getAPIKey()).getId();
            String cityCountry = WeatherAPI.ofCity(cityName, properties.getProperty("apikey")).getSys().get(Sys.COUNTRY.toString());
            System.out.println("City country from response:: " + cityCountry);

            Assertions.assertEquals(getCityCountry(cityID), cityCountry);
        } catch (FailedHttpConnectionException e) {
            e.printStackTrace();
        };
    }

    @ParameterizedTest
    @CsvSource({"leeds", "berlin",
                "lasvegas", "johannesburg",
                "brasilia", "sydney",
                "bangkok", "cairo",
                "quebec", "honolulu"})
    @DisplayName("Does the current city have a valid timezone?")
    void isResponseCityTimezoneValid(String cityName) {
        String cityID = null;
        try {
            cityID = WeatherAPI.ofCity(cityName, WeatherAPI.getAPIKey()).getId();
            String cityTimezone = WeatherAPI.ofCity(cityName, properties.getProperty("apikey")).getTimezone();
            System.out.println("City timezone from response:: " + cityTimezone);

            Assertions.assertEquals(getCityTimezone(cityID), cityTimezone);
        } catch (FailedHttpConnectionException e) {
            e.printStackTrace();
        };
    }
}

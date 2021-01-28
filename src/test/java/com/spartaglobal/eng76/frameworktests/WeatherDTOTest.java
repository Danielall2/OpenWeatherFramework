package com.spartaglobal.eng76.frameworktests;

import com.spartaglobal.eng76.framework.connectionmanager.ConnectionManager;
import com.spartaglobal.eng76.framework.dto.WeatherDTO;
import com.spartaglobal.eng76.framework.weatherapi.WeatherAPI;
import org.junit.jupiter.api.*;

import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.fail;

class WeatherDTOTest {
    private static WeatherDTO weatherDTO;
    private static Properties properties;
    private static String apikey;
    private static int cityId = 2172797;

    @BeforeAll
    static void setup(){
        apikey = WeatherAPI.getAPIKey();
        weatherDTO = WeatherAPI.ofCity(cityId, apikey);
    }

    @Test
    @DisplayName("getCoord returns hashmap of Strings")
    void getCoordReturnsHashmapOfStrings() {
        Assertions.assertEquals(HashMap.class, weatherDTO.getCoord().getClass());
        Assertions.assertEquals(String.class, weatherDTO.getCoord().values().toArray()[0].getClass());
        Assertions.assertEquals(String.class, weatherDTO.getCoord().keySet().toArray()[0].getClass());
    }

    @Test
    @DisplayName("getWind returns hashmap of Strings")
    void getWindReturnsHashmapOfStrings() {
        Assertions.assertEquals(HashMap.class, weatherDTO.getWind().getClass());
        Assertions.assertEquals(String.class, weatherDTO.getWind().values().toArray()[0].getClass());
        Assertions.assertEquals(String.class, weatherDTO.getWind().keySet().toArray()[0].getClass());
    }

    @Test
    @DisplayName("getMain returns hashmap of Strings")
    void getMainReturnsHashmapOfStrings() {
        Assertions.assertEquals(HashMap.class, weatherDTO.getMain().getClass());
        Assertions.assertEquals(String.class, weatherDTO.getMain().values().toArray()[0].getClass());
        Assertions.assertEquals(String.class, weatherDTO.getMain().keySet().toArray()[0].getClass());
    }

    @Test
    @DisplayName("getClouds returns hashmap of Strings")
    void getCloudsReturnsHashmapOfStrings() {
        Assertions.assertEquals(HashMap.class, weatherDTO.getClouds().getClass());
        Assertions.assertEquals(String.class, weatherDTO.getClouds().values().toArray()[0].getClass());
        Assertions.assertEquals(String.class, weatherDTO.getClouds().keySet().toArray()[0].getClass());
    }

    @Test
    @DisplayName("getSnow returns hashmap of Strings if snow field exists")
    void getSnowReturnsHashmapOfStrings() {
        Assumptions.assumeTrue(weatherDTO.getSnow() != null);
        Assertions.assertEquals(HashMap.class, weatherDTO.getSnow().getClass());
        Assertions.assertEquals(String.class, weatherDTO.getSnow().values().toArray()[0].getClass());
        Assertions.assertEquals(String.class, weatherDTO.getSnow().keySet().toArray()[0].getClass());
    }

    @Test
    @DisplayName("getRain returns hashmap of Strings if rain field exists")
    void getRainReturnsHashmapOfStrings() {
        Assumptions.assumeTrue(weatherDTO.getRain() != null);
        Assertions.assertEquals(HashMap.class, weatherDTO.getRain().getClass());
        Assertions.assertEquals(String.class, weatherDTO.getRain().values().toArray()[0].getClass());
        Assertions.assertEquals(String.class, weatherDTO.getRain().keySet().toArray()[0].getClass());
    }

    @Test
    @DisplayName("getWeather returns list of hashmap of Strings")
    void getWeatherReturnsListOfHashmapOfStrings() {
        Assertions.assertTrue(weatherDTO.getWeather() instanceof List);
        Assertions.assertEquals(HashMap.class, weatherDTO.getWeather().get(0).getClass());
        Assertions.assertEquals(String.class, weatherDTO.getWeather().get(0).values().toArray()[0].getClass());
        Assertions.assertEquals(String.class, weatherDTO.getWeather().get(0).keySet().toArray()[0].getClass());
    }

    @Test
    @DisplayName("getBase returns String")
    void getBaseReturnsString() {
        Assertions.assertEquals(weatherDTO.getBase().getClass(), String.class);
    }

    @Test
    @DisplayName("getVisibility returns String")
    void getVisibilityReturnsString() {
        Assertions.assertEquals(weatherDTO.getVisibility().getClass(), String.class);
    }

    @Test
    @DisplayName("getRain returns correct values")
    void getRainReturnsCorrectValues() {
        Assertions.assertNull(weatherDTO.getRain());
    }

    @Test
    @DisplayName("getId returns correct value")
    void getIdReturnsCorrectValues() {
        Assertions.assertEquals(cityId, Integer.parseInt(weatherDTO.getId()));
    }

    @Test
    @DisplayName("getTimezone returns correct value")
    void getTimezoneReturnsCorrectValue() {
        Assertions.assertEquals("36000", weatherDTO.getTimezone());
    }

    @Test
    @DisplayName("getName returns correct value")
    void getNameReturnsCorrectValue() {
        Assertions.assertEquals("Cairns", weatherDTO.getName());
    }

    @Test
    @DisplayName("getCod returns correct value")
    void getCodReturnsCorrectValue() {
        Assertions.assertEquals("200", weatherDTO.getCod());
    }

    @Test
    @DisplayName("getSys returns correct values")
    void getSysReturnsCorrectValues() {
        HashMap<String, String> sys = weatherDTO.getSys();
        Assertions.assertEquals(sys.get("type"), "1");
        Assertions.assertEquals(sys.get("id"), "9490");
        Assertions.assertEquals(sys.get("country"), "AU");
        Assertions.assertEquals(sys.get("sunrise"), "1611777781");
        Assertions.assertEquals(sys.get("sunset"), "1611824184");
    }
    
    @Test
    @DisplayName("getConnectionManager returns ConnectionManager")
    void getConnectionManagerReturnsConnectionManager() {
        Assertions.assertEquals(ConnectionManager.class, weatherDTO.getConnectionManager().getClass());
    }

    @Test
    @DisplayName("getDt gets time in the last 3 hours")
    void getDtGetsTimeInTheLast3Hours() {
        long currentTimeSeconds = System.currentTimeMillis()/1000;
        long apiTime = Long.parseLong(weatherDTO.getDt());
        System.out.println(apiTime);
        System.out.println(currentTimeSeconds - 60*60*3);
        Assertions.assertTrue(apiTime > currentTimeSeconds - 60*60*3);
    }
    
    @Test
    @DisplayName("getLocalDate converts Dt correctly")
    void getLocalDateConvertsDtCorrectly() {
        long dtMillis = Long.parseLong(weatherDTO.getDt())*1000;
        LocalDate dtDate = Instant.ofEpochMilli(dtMillis).atZone(ZoneId.systemDefault()).toLocalDate();
        Assertions.assertEquals(LocalDate.now(), dtDate);
    }
}
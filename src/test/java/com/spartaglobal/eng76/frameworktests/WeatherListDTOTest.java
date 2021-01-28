package com.spartaglobal.eng76.frameworktests;

import com.spartaglobal.eng76.framework.connectionmanager.ConnectionManager;
import com.spartaglobal.eng76.framework.dto.WeatherDTO;
import com.spartaglobal.eng76.framework.dto.WeatherListDTO;
import com.spartaglobal.eng76.framework.exceptions.FailedHttpConnectionException;
import com.spartaglobal.eng76.framework.weatherapi.WeatherAPI;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

class WeatherListDTOTest {

    private static WeatherListDTO weatherListDTO;
    private static WeatherListDTO weatherListDTOCircle;
    private static WeatherListDTO weatherListDTOBox;
    private static String apikey;
    private static int[] cityIds = new int[]{524901,703448,2643743,2172797};

    @BeforeAll
    static void setup(){
        apikey = WeatherAPI.getAPIKey();
        try {
            weatherListDTO = WeatherAPI.ofCities(cityIds, apikey);
            weatherListDTOBox = WeatherAPI.ofCitiesInRectangle(12,32,15,37,10, apikey);
            weatherListDTOCircle = WeatherAPI.ofCitiesInCircle(55.5, 37.5, apikey);
        } catch (FailedHttpConnectionException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("getMessage returns String if message field exists")
    void testGetMessage() {
        Assumptions.assumeTrue(weatherListDTO.getMessage() != null);
        Assertions.assertEquals(String.class, weatherListDTO.getMessage().getClass());
    }

    @Test
    @DisplayName("getMessage returns String if message field exists (Box)")
    void testGetMessageBox() {
        Assumptions.assumeTrue(weatherListDTOBox.getMessage() != null);
        Assertions.assertEquals(String.class, weatherListDTOBox.getMessage().getClass());
    }

    @Test
    @DisplayName("getMessage returns String if message field exists (Circle)")
    void testGetMessageCircle() {
        Assumptions.assumeTrue(weatherListDTOCircle.getMessage() != null);
        Assertions.assertEquals(String.class, weatherListDTOCircle.getMessage().getClass());
    }

    @Test
    @DisplayName("getCod returns String if cod field exists")
    void testGetCoord() {
        Assumptions.assumeTrue(weatherListDTO.getCod() != null);
        Assertions.assertEquals(String.class, weatherListDTO.getCod().getClass());
    }

    @Test
    @DisplayName("getCod returns String if cod field exists (Box)")
    void testGetCoordBox() {
        Assumptions.assumeTrue(weatherListDTOBox.getCod() != null);
        Assertions.assertEquals(String.class, weatherListDTOBox.getCod().getClass());
    }

    @Test
    @DisplayName("getCod returns String if cod field exists (Circle)")
    void testGetCoordCircle() {
        Assumptions.assumeTrue(weatherListDTOCircle.getCod() != null);
        Assertions.assertEquals(String.class, weatherListDTOCircle.getCod().getClass());
    }

    @Test
    @DisplayName("getCount returns String if count field exists")
    void testGetCount() {
        Assumptions.assumeTrue(weatherListDTO.getCount() != null);
        Assertions.assertEquals(String.class, weatherListDTO.getCount().getClass());
    }

    @Test
    @DisplayName("getCount returns String if count field exists (Box)")
    void testGetCountBox() {
        Assumptions.assumeTrue(weatherListDTOBox.getCount() != null);
        Assertions.assertEquals(String.class, weatherListDTOBox.getCount().getClass());
    }

    @Test
    @DisplayName("getCount returns String if count field exists (Circle)")
    void testGetCountCircle() {
        Assumptions.assumeTrue(weatherListDTOCircle.getCount() != null);
        Assertions.assertEquals(String.class, weatherListDTOCircle.getCount().getClass());
    }

    @Test
    @DisplayName("getCalctime returns String if calctime field exists")
    void testGetCalctime() {
        Assumptions.assumeTrue(weatherListDTO.getCalctime() != null);
        Assertions.assertEquals(String.class, weatherListDTO.getCalctime().getClass());
    }

    @Test
    @DisplayName("getCalctime returns String if calctime field exists (Box)")
    void testGetCalctimeBox() {
        Assumptions.assumeTrue(weatherListDTOBox.getCalctime() != null);
        Assertions.assertEquals(String.class, weatherListDTOBox.getCalctime().getClass());
    }

    @Test
    @DisplayName("getCalctime returns String if calctime field exists (Circle)")
    void testGetCalctimeCircle() {
        Assumptions.assumeTrue(weatherListDTOCircle.getCalctime() != null);
        Assertions.assertEquals(String.class, weatherListDTOCircle.getCalctime().getClass());
    }

    @Test
    @DisplayName("getCnt as String")
    void testGetCnt() {
        Assumptions.assumeTrue(weatherListDTO.getCount() != null);
        Assertions.assertEquals(String.class, weatherListDTO.getCount().getClass());
    }

    @Test
    @DisplayName("getCnt as String (Box)")
    void testGetCntBox() {
        Assumptions.assumeTrue(weatherListDTOBox.getCount() != null);
        Assertions.assertEquals(String.class, weatherListDTOBox.getCount().getClass());
    }

    @Test
    @DisplayName("getCnt as String (Circle)")
    void testGetCntCircle() {
        Assumptions.assumeTrue(weatherListDTOCircle.getCount() != null);
        Assertions.assertEquals(String.class, weatherListDTOCircle.getCount().getClass());
    }

    @Test
    @DisplayName("getList as ArrayList of WeatherDTOs")
    void testGetList() {
        Assertions.assertEquals(ArrayList.class, weatherListDTO.getList().getClass());
        for (int i = 0; i < 4; i++) {
            Assertions.assertEquals(WeatherDTO.class, weatherListDTO.getList().get(i).getClass());
        }
    }

    @Test
    @DisplayName("getList as ArrayList of WeatherDTOs (Box)")
    void testGetListBox() {
        Assertions.assertEquals(ArrayList.class, weatherListDTOBox.getList().getClass());
        for (int i = 0; i < 4; i++) {
            Assertions.assertEquals(WeatherDTO.class, weatherListDTOBox.getList().get(i).getClass());
        }
    }

    @Test
    @DisplayName("getList as ArrayList of WeatherDTOs (Circle)")
    void testGetListCircle() {
        Assertions.assertEquals(ArrayList.class, weatherListDTOCircle.getList().getClass());
        for (int i = 0; i < 4; i++) {
            Assertions.assertEquals(WeatherDTO.class, weatherListDTOCircle.getList().get(i).getClass());
        }
    }

    @Test
    @DisplayName("getName inside first WeatherDTO returns correct name")
    void testGetName() {
        Assertions.assertEquals("Moscow", weatherListDTO.getList().get(0).getName());
    }

    @Test
    @DisplayName("getConnectionManager returns ConnectionManager")
    void testGetConnectionManager() {
        Assertions.assertEquals(ConnectionManager.class, weatherListDTO.getConnectionManager().getClass());
    }

}

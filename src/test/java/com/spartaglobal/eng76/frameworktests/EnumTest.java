package com.spartaglobal.eng76.frameworktests;

import com.spartaglobal.eng76.framework.dto.Enums.*;
import com.spartaglobal.eng76.framework.urlbuilder.OptionalParam;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import java.util.Map;


import static org.junit.jupiter.api.Assertions.fail;

public class EnumTest {

    @Test
    @DisplayName("Clouds All toString returns correct string")
    void cloudsAllToStringReturnsCorrectString() {
        Assertions.assertEquals("all", Clouds.ALL.toString());
    }

    @Test
    @DisplayName("Clouds Today toString returns correct string")
    void cloudsTodayToStringReturnsCorrectString() {
        Assertions.assertEquals("today", Clouds.TODAY.toString());
    }

    @Test
    @DisplayName("Coordinates Longitude toString returns correct string")
    void coordinatesLongitudeToStringReturnsCorrectString() {
        Assertions.assertEquals("lon", Coordinates.LONGITUDE.toString());
    }

    @Test
    @DisplayName("Coordinates Latitude toString returns correct string")
    void coordinatesLatitudeToStringReturnsCorrectString() {
        Assertions.assertEquals("lat", Coordinates.LATITUDE.toString());
    }

    @Test
    @DisplayName("Main Temperature toString returns correct string")
    void mainTemperatureToStringReturnsCorrectString() {
        Assertions.assertEquals("temp", Main.TEMPERATURE.toString());
    }

    @Test
    @DisplayName("Main MinTemperature toString returns correct string")
    void mainMinTemperatureToStringReturnsCorrectString() {
        Assertions.assertEquals("temp_min", Main.MIN_TEMPERATURE.toString());
    }

    @Test
    @DisplayName("Main MaxTemperature toString returns correct string")
    void mainMaxTemperatureToStringReturnsCorrectString() {
        Assertions.assertEquals("temp_max", Main.MAX_TEMPERATURE.toString());
    }

    @Test
    @DisplayName("Main Pressure toString returns correct string")
    void mainPressureToStringReturnsCorrectString() {
        Assertions.assertEquals("pressure", Main.PRESSURE.toString());
    }

    @Test
    @DisplayName("Main Humidity toString returns correct string")
    void mainHumidityToStringReturnsCorrectString() {
        Assertions.assertEquals("humidity", Main.HUMIDITY.toString());
    }

    @Test
    @DisplayName("Main PressureSeaLevel toString returns correct string")
    void mainPressureSeaLevelToStringReturnsCorrectString() {
        Assertions.assertEquals("sea_level", Main.PRESSURE_SEA_LEVEL.toString());
    }

    @Test
    @DisplayName("Main PressureGroundLevel toString returns correct string")
    void mainPressureGroundLevelToStringReturnsCorrectString() {
        Assertions.assertEquals("grnd_level", Main.PRESSURE_GROUND_LEVEL.toString());
    }

    @Test
    @DisplayName("Rain RainOneHour toString returns correct string")
    void rainRainOneHourToStringReturnsCorrectString() {
        Assertions.assertEquals("1h", Rain.RAIN_ONE_HOUR.toString());
    }

    @Test
    @DisplayName("Rain RainThreeHours toString returns correct string")
    void rainRainThreeHoursToStringReturnsCorrectString() {
        Assertions.assertEquals("3h", Rain.RAIN_THREE_HOURS.toString());
    }

    @Test
    @DisplayName("Snow SnowOneHour toString returns correct string")
    void snowSnowOneHourToStringReturnsCorrectString() {
        Assertions.assertEquals("1h", Snow.SNOW_ONE_HOUR.toString());
    }

    @Test
    @DisplayName("Snow SnowThreeHours toString returns correct string")
    void snowSnowThreeHoursToStringReturnsCorrectString() {
        Assertions.assertEquals("3h", Snow.SNOW_THREE_HOURS.toString());
    }

    @Test
    @DisplayName("Sys Type toString returns correct string")
    void sysTypeToStringReturnsCorrectString() {
        Assertions.assertEquals("type", Sys.TYPE.toString());
    }

    @Test
    @DisplayName("Sys Id toString returns correct string")
    void sysIdReturnsCorrectString() {
        Assertions.assertEquals("id", Sys.ID.toString());
    }

    @Test
    @DisplayName("Sys Country toString returns correct string")
    void sysCountryToStringReturnsCorrectString() {
        Assertions.assertEquals("country", Sys.COUNTRY.toString());
    }

    @Test
    @DisplayName("Sys Sunrise toString returns correct string")
    void sysSunriseToStringReturnsCorrectString() {
        Assertions.assertEquals("sunrise", Sys.SUNRISE.toString());
    }

    @Test
    @DisplayName("Sys Sunset toString returns correct string")
    void sysSunsetToStringReturnsCorrectString() {
        Assertions.assertEquals("sunset", Sys.SUNSET.toString());
    }

    @Test
    @DisplayName("Weather Id toString returns correct string")
    void weatherIdToStringReturnsCorrectString() {
        Assertions.assertEquals("id", Weather.ID.toString());
    }

    @Test
    @DisplayName("Weather Main toString returns correct string")
    void weatherMainToStringReturnsCorrectString() {
        Assertions.assertEquals("main", Weather.MAIN.toString());
    }

    @Test
    @DisplayName("Weather Description toString returns correct string")
    void weatherDescriptionToStringReturnsCorrectString() {
        Assertions.assertEquals("description", Weather.DESCRIPTION.toString());
    }

    @Test
    @DisplayName("Weather Icon toString returns correct string")
    void weatherIconToStringReturnsCorrectString() {
        Assertions.assertEquals("icon", Weather.ICON.toString());
    }

    @Test
    @DisplayName("Wind Speed toString returns correct string")
    void windSpeedToStringReturnsCorrectString() {
        Assertions.assertEquals("speed", Wind.SPEED.toString());
    }

    @Test
    @DisplayName("Wind Degrees toString returns correct string")
    void windDegreesToStringReturnsCorrectString() {
        Assertions.assertEquals("deg", Wind.DEGREES.toString());
    }

    @Test
    @DisplayName("Wind Gust toString returns correct string")
    void windGustToStringReturnsCorrectString() {
        Assertions.assertEquals("gust", Wind.GUST.toString());
    }


    @Test
    @DisplayName("OptionalParam Mode toString returns correct string")
    void OptionalParamToStringReturnsCorrectString() {
        Assertions.assertEquals("mode", OptionalParam.MODE.toString());
    }

    @Test
    @DisplayName("OptionalParam Units toString returns correct string")
    void OptionalParamUnitsToStringReturnsCorrectString() {
        Assertions.assertEquals("units", OptionalParam.UNITS.toString());
    }

    @Test
    @DisplayName("OptionalParam Count toString returns correct string")
    void OptionalParamCountToStringReturnsCorrectString() {
        Assertions.assertEquals("cnt", OptionalParam.COUNT.toString());
    }

    @Test
    @DisplayName("OptionalParam Lang toString returns correct string")
    void OptionalParamLangToStringReturnsCorrectString() {
        Assertions.assertEquals("lang", OptionalParam.LANG.toString());
    }

    @Test
    @DisplayName("OptionalParam Mode ofValue returns correct Map")
    void optionalParamModeOfValueReturnsCorrectMap() {
        Map.Entry<String, String> map = Map.entry("mode", "val");
        Assertions.assertEquals(map, OptionalParam.MODE.ofValue("val"));
    }

    @Test
    @DisplayName("OptionalParam Units ofValue returns correct Map")
    void optionalParamUnitsOfValueReturnsCorrectMap() {
        Map.Entry<String, String> map = Map.entry("units", "val");
        Assertions.assertEquals(map, OptionalParam.UNITS.ofValue("val"));
    }

    @Test
    @DisplayName("OptionalParam Count ofValue returns correct Map")
    void optionalParamCountOfValueReturnsCorrectMap() {
        Map.Entry<String, String> map = Map.entry("cnt", "val");
        Assertions.assertEquals(map, OptionalParam.COUNT.ofValue("val"));
    }

    @Test
    @DisplayName("OptionalParam Lang ofValue returns correct Map")
    void optionalParamLangOfValueReturnsCorrectMap() {
        Map.Entry<String, String> map = Map.entry("lang", "val");
        Assertions.assertEquals(map, OptionalParam.LANG.ofValue("val"));
    }
}

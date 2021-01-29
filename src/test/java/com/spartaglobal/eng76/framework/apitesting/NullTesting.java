package com.spartaglobal.eng76.framework.apitesting;

import com.spartaglobal.eng76.framework.dto.Enums.Coordinates;
import com.spartaglobal.eng76.framework.dto.WeatherDTO;
import com.spartaglobal.eng76.framework.weatherapi.WeatherAPI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class NullTesting {
	static Properties properties;

	@BeforeAll
	static void setup() {
		try {
			properties.load(new FileReader("src/test/resources/apikey.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@ParameterizedTest
	@CsvSource({"leeds" ,
			"berlin",
			"lasvegas",
			"johannesburg",
			"brasilia"})
	@DisplayName("Are there any null values for city id?")
	void isCityIdNotNull(String city){
		WeatherDTO currentWeather = WeatherAPI.ofCity(city,properties.getProperty("apikey"));

		Assertions.assertNotNull(currentWeather.getId());
	}


	@ParameterizedTest
	@CsvSource({"leeds" ,
			"berlin",
			"lasvegas",
			"johannesburg",
			"brasilia"})
	@DisplayName("Are there any null values for country code?")
	void isCountryCodeNotNull(String city){
		WeatherDTO currentWeather = WeatherAPI.ofCity(city,properties.getProperty("apikey"));
		HashMap<String, String> sys = currentWeather.getSys();

		Assertions.assertNotNull(sys.get("country"));
	}

	@ParameterizedTest
	@CsvSource({"leeds" ,
			"berlin",
			"lasvegas",
			"johannesburg",
			"brasilia"})
	@DisplayName("Are there any null values for coordinates?")
	void areCoordinatesNotNull(String city){
		WeatherDTO currentWeather = WeatherAPI.ofCity(city,properties.getProperty("apikey"));
		HashMap<String, String> coord = currentWeather.getCoord();

		Assertions.assertNotNull(coord);
		Assertions.assertNotNull(coord.get(Coordinates.LONGITUDE.toString()));
		Assertions.assertNotNull(coord.get(Coordinates.LATITUDE.toString()));
	}


	@ParameterizedTest
	@CsvSource({"leeds" ,
			"berlin",
			"lasvegas",
			"johannesburg",
			"brasilia"})
	@DisplayName("Are there any null values for city name?")
	void isCityNameNotNull(String city){
		WeatherDTO currentWeather = WeatherAPI.ofCity(city,properties.getProperty("apikey"));

		Assertions.assertNotNull(currentWeather.getName());
	}

	@ParameterizedTest
	@CsvSource({"leeds" ,
			"berlin",
			"lasvegas",
			"johannesburg",
			"brasilia"})
	@DisplayName("Are there any null values for DT?")
	void isDtNotNull(String city){
		WeatherDTO currentWeather = WeatherAPI.ofCity(city,properties.getProperty("apikey"));

		Assertions.assertNotNull(currentWeather.getDt());
	}

	@ParameterizedTest
	@CsvSource({"leeds" ,
			"berlin",
			"lasvegas",
			"johannesburg",
			"brasilia"})
	@DisplayName("Are there any null values for timezone?")
	void isTimezoneNotNull(String city){
		WeatherDTO currentWeather = WeatherAPI.ofCity(city,properties.getProperty("apikey"));

		Assertions.assertNotNull(currentWeather.getTimezone());
	}

	@ParameterizedTest
	@CsvSource({"leeds" ,
			"berlin",
			"lasvegas",
			"johannesburg",
			"brasilia"})
	@DisplayName("Are there any null values for status code?")
	void isCodNotNull(String city){
		WeatherDTO currentWeather = WeatherAPI.ofCity(city,properties.getProperty("apikey"));

		Assertions.assertNotNull(currentWeather.getCod());
	}


	@ParameterizedTest
	@CsvSource({"leeds" ,
			"berlin",
			"lasvegas",
			"johannesburg",
			"brasilia"})
	@DisplayName("Are there any null values for sys?")
	void isSysNotNull(String city){
		WeatherDTO currentWeather = WeatherAPI.ofCity(city,properties.getProperty("apikey"));

		HashMap<String, String> sys = currentWeather.getSys();
		Assertions.assertNotNull(sys);

		for(String key: sys.keySet()){
			Assertions.assertNotNull(sys.get(key));
		}
	}

	@ParameterizedTest
	@CsvSource({"leeds" ,
			"berlin",
			"lasvegas",
			"johannesburg",
			"brasilia"})
	@DisplayName("Are there any null values for visibility?")
	void isVisibilityNotNull(String city){
		WeatherDTO currentWeather = WeatherAPI.ofCity(city,properties.getProperty("apikey"));

		Assertions.assertNotNull(currentWeather.getVisibility());
	}


	@ParameterizedTest
	@CsvSource({"leeds" ,
			"berlin",
			"lasvegas",
			"johannesburg",
			"brasilia"})
	@DisplayName("Are there any null values for weather?")
	void isWeatherFieldNotNull(String city){
		WeatherDTO currentWeather = WeatherAPI.ofCity(city,properties.getProperty("apikey"));

		List<HashMap<String, String>> weathers = currentWeather.getWeather();
		Assertions.assertNotNull(weathers);

		for(HashMap <String,String> weather : weathers) {

			Assertions.assertNotNull(weather);
			for (String key : weather.keySet()) {
				Assertions.assertNotNull(weather.get(key));
			}
		}
	}

	@ParameterizedTest
	@CsvSource({"leeds" ,
			"berlin",
			"lasvegas",
			"johannesburg",
			"brasilia"})
	@DisplayName("Are there any null values for base?")
	void isBaseNotNull(String city){
		WeatherDTO currentWeather = WeatherAPI.ofCity(city,properties.getProperty("apikey"));

		Assertions.assertNotNull(currentWeather.getBase());
	}


	@ParameterizedTest
	@CsvSource({"leeds" ,
			"berlin",
			"lasvegas",
			"johannesburg",
			"brasilia"})
	@DisplayName("Are there any null values for main?")
	void isMainNotNull(String city){
		WeatherDTO currentWeather = WeatherAPI.ofCity(city,properties.getProperty("apikey"));

		HashMap<String, String> main = currentWeather.getMain();
		Assertions.assertNotNull(main);

		for(String key: main.keySet()){
			Assertions.assertNotNull(main.get(key));
		}
	}

	@ParameterizedTest
	@CsvSource({"leeds" ,
			"berlin",
			"lasvegas",
			"johannesburg",
			"brasilia"})
	@DisplayName("Are there any null values for wind?")
	void isWindNotNull(String city){
		WeatherDTO currentWeather = WeatherAPI.ofCity(city,properties.getProperty("apikey"));

		HashMap<String, String> wind = currentWeather.getWind();
		Assertions.assertNotNull(wind);

		for(String key: wind.keySet()){
			Assertions.assertNotNull(wind.get(key));
		}
	}

	@ParameterizedTest
	@CsvSource({"leeds" ,
			"berlin",
			"lasvegas",
			"johannesburg",
			"brasilia"})
	@DisplayName("Are there any null values for clouds?")
	void isCloudsNotNull(String city){
		WeatherDTO currentWeather = WeatherAPI.ofCity(city,properties.getProperty("apikey"));

		HashMap<String, String> clouds = currentWeather.getClouds();
		Assertions.assertNotNull(clouds);

		for(String key: clouds.keySet()){
			Assertions.assertNotNull(clouds.get(key));
		}
	}

}

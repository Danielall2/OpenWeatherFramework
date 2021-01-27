package com.spartaglobal.eng76.framework.weatherapi;

import com.spartaglobal.eng76.framework.dto.Enums.Main;
import com.spartaglobal.eng76.framework.dto.WeatherDTO;
import com.spartaglobal.eng76.framework.injector.Injector;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class WeatherAPI {

    public static void main(String[] args) {
        Injector injector = new Injector();
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("src/test/resources/apikey.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("url : " + properties.getProperty("url"));

        WeatherDTO weatherDTO = injector.injectIntoWeatherDTO(properties.getProperty("url"));

        System.out.println(weatherDTO.getBase());

        System.out.println(weatherDTO.getMain().get(Main.PRESSURE.toString()));

    }


}

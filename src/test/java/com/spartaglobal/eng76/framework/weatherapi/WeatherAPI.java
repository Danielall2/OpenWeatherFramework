package com.spartaglobal.eng76.framework.weatherapi;

import com.spartaglobal.eng76.framework.dto.Enums.Main;
import com.spartaglobal.eng76.framework.dto.WeatherDTO;
import com.spartaglobal.eng76.framework.injector.Injector;
import com.spartaglobal.eng76.framework.urlbuilder.URLBuilder;

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

        System.out.println("apikey : " + properties.getProperty("apikey"));

        String url = URLBuilder.ofCity("London", properties.getProperty("apikey")).toString();

        WeatherDTO weatherDTO = injector.injectIntoWeatherDTO(url);

        System.out.println(weatherDTO.getBase());

        System.out.println(weatherDTO.getMain().get(Main.PRESSURE.toString()));

    }


}

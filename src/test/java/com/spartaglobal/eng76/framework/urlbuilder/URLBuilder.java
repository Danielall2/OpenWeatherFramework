package com.spartaglobal.eng76.framework.urlbuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is creating the URLs needed to create a call on WeatherAPI.
 * @author Samurah
 * @version 1.0
 */
public class URLBuilder {
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/";

    private String baseUrl;
    private String path;
    private String query;
    private String apikey;
    private boolean searchSelected;

    private URLBuilder() {
    }

    private URLBuilder(String baseUrl, String path, String query, String apikey) {
        this.baseUrl = baseUrl;
        if (path.charAt(path.length() - 1) == '/') {
            path = path.substring(0, path.length() - 1);
        }
        this.path = path;
        this.query = paramsNoAPIKey(splitParam(query));
        this.apikey = apikey;
    }

    public static URLBuilder create(String url, String apikey) {
        return decode(url, apikey);
    }

    public static URLBuilder decode(String url, String apikey) {
        Pattern pattern = Pattern.compile("^(?<baseurl>(?<scheme>https?://)?(?<hostname>[\\w.-]+))(?<path>/[\\w+/.]+)?(\\?(?<query>[^#]+)(#.*)?)?$");
        Matcher matcher = pattern.matcher(url);
        if (apikey.isBlank()) {
            throw new IllegalArgumentException("Missing API key");
        }
        if (matcher.matches()) {
            return new URLBuilder(matcher.group("baseurl"), matcher.group("path"), matcher.group("query"), apikey);
        } else {
            throw new IllegalArgumentException("Bad URL format.");
        }
    }

    private static String paramsNoAPIKey(Collection<Map.Entry<String, String>> params) {
        if (params != null) {
            StringBuilder query = new StringBuilder();
            for (Map.Entry<String, String> param : params) {
                if (!param.getKey().equalsIgnoreCase("appid")) {
                    if (!query.toString().isBlank()) {
                        query.append("&");
                    }
                    query.append(param.getKey()).append("=").append(param.getValue());
                }
            }
            return query.toString();
        }
        return null;
    }

    private static Collection<Map.Entry<String, String>> splitParam(String query) {
        if (query != null) {
            ArrayList<Map.Entry<String, String>> list = new ArrayList<>();
            String[] params = query.split("&");
            for (String param : params) {
                if (param.matches("[^=]*=[^=]*")) {
                    String[] sParam = param.split("=");
                    list.add(Map.entry(sParam[0], sParam[1]));
                }
            }
            return list;
        }
        return null;
    }

    public static URLBuilder ofCity(String cityName, String apikey) {
        return create(BASE_URL + "weather?" + "q=" + cityName, apikey);
    }

    public static URLBuilder ofCity(String cityName, String stateCode, String apikey) {
        return create(BASE_URL + "weather?" + "q=" + cityName + "," + stateCode, apikey);
    }

    public static URLBuilder ofCity(String cityName, String stateCode, String countryCode, String apikey) {
        return create(BASE_URL + "weather?" + "q=" + cityName + "," + stateCode + "," + countryCode, apikey);
    }

    public static URLBuilder ofCity(int cityId, String apikey) {
        return create(BASE_URL + "weather?" + "id=" + cityId, apikey);
    }

    public static URLBuilder ofCities(int[] citiesId, String apikey) {
        StringBuilder params = new StringBuilder();
        for (int cityId : citiesId) {
            if (!params.toString().isBlank()) {
                params.append(",");
            }
            params.append(cityId);
        }
        return create(BASE_URL + "group?" + "id=" + params.toString(), apikey);
    }

    public static URLBuilder ofCitiesInRectangle(long lon_left, long lat_bottom, long lon_right, long lat_top, int zoom, String apikey) {
        return create(BASE_URL + "box/city?" + "bbox=" + lon_left + "," + lat_bottom + "," + lon_right + "," + lat_top + "," + zoom, apikey);
    }

    public static URLBuilder ofCitiesInCircle(long lat, long lon, String apikey) {
        return create(BASE_URL + "find?" + "lat=" + lat + "&lon=" + lon, apikey);
    }

    public static URLBuilder ofZipCode(String zipCode, String apikey) {
        return create(BASE_URL + "weather?" + "zip=" + zipCode, apikey);
    }

    public static URLBuilder ofZipCode(String zipCode, String countryCode, String apikey) {
        return create(BASE_URL + "weather?" + "zip=" + zipCode + "," + countryCode, apikey);
    }

    @Override
    public String toString() {
        StringBuilder urlBuilder = new StringBuilder(baseUrl);
        if (!path.isBlank()) {
            urlBuilder.append(path);
            if (this.query != null && !this.query.isBlank()) {
                urlBuilder.append("?").append(query).append("&").append("appid").append("=").append(apikey);
            } else {
                urlBuilder.append("?").append("appid").append("=").append(apikey);
            }
        }
        return urlBuilder.toString();
    }
}

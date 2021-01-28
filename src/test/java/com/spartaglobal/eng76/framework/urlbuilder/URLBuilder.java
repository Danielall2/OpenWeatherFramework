package com.spartaglobal.eng76.framework.urlbuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is creating the URLs needed to create a call on WeatherAPI.
 * @author Samurah
 * @version 1.2
 * @since 1.0
 */
public class URLBuilder {
    /**
     * This is the base url used to create specific calls in {@code ofCity} methods.
     */
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/";

    private String baseUrl;
    private String path;
    private String query;
    private String apikey;

    private URLBuilder() {
    }

    /**
     * Internal use of constructor.
     * @param baseUrl - url containing scheme and hostname
     * @param path - path
     * @param query - query parameters
     * @param apikey - the api key used to access the data
     */
    private URLBuilder(String baseUrl, String path, String query, String apikey) {
        this.baseUrl = baseUrl;
        if (path.charAt(path.length() - 1) == '/') {
            path = path.substring(0, path.length() - 1);
        }
        this.path = path;
        this.query = paramsNoAPIKey(splitParam(query));
        this.apikey = apikey;
    }

    /**
     * Adds a parameter in {@code query};
     * @param key String value
     * @param value String value
     * @return the same instance of URLBuilder, with the extra parameter added
     */
    public URLBuilder addParam(String key, String value){
        Collection<Map.Entry<String, String>> params = splitParam(query);
        params.add(Map.entry(key, value));
        this.query = paramsNoAPIKey(params);
        return this;
    }

    /**
     * Adds a parameter in query
     * @param key String
     * @param value String
     * @return the same instance of URLBuilder, with the extra parameter added.
     */
    public URLBuilder addParam(OptionalParam key, String value){
        return addParam(key.toString(), value);
    }

    public static URLBuilder create(String url, String apikey) {
        return decode(url, apikey);
    }

    /**
     * Decodes the url and creates a new URLBuilder object having the apikey provided.
     * @param url String
     * @param apikey String
     * @return URLBuilder instance
     */
    private static URLBuilder decode(String url, String apikey) {
        if(url!=null && apikey != null) {
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
        }else{
            throw new IllegalArgumentException("Null values not allowed!");
        }
    }

    /**
     * Generates a string that is excluding API key if contained.
     * @param params Collection of {@code Map.Entry<String, String>}
     * @return String
     */
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

    /**
     * Generates a Collection of parameters from {@code query} provided.
     * @param query String
     * @return {@code Collection<Map.Entry<String,String>>}
     */
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

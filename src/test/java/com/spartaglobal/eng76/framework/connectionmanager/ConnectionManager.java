package com.spartaglobal.eng76.framework.connectionmanager;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class ConnectionManager {
    private HttpResponse<String> httpResponse = null;
    private HttpClient httpClient;
    private HttpRequest httpRequest;
    private HttpHeaders httpHeaders = null;
    private Map<String, List<String>> headersMap;

    public ConnectionManager() {
    }

    public void connectToAPI(String url) {
        try {
            httpClient = HttpClient.newHttpClient();
            httpRequest = HttpRequest.newBuilder().uri(URI.create(url)).build();
            httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            httpHeaders = httpResponse.headers();
            headersMap = httpHeaders.map();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    public int getStatusCode() {
        return httpResponse.statusCode();
    }

    public String getHttpVersion() {
        HttpClient.Version version = httpResponse.version();
        return version.name();
    }

    public String getHttpResponseBody() {
        return httpResponse.body();
    }

    public void printAllResponseHeaders() {
        headersMap.entrySet().stream().forEach(e -> System.out.println(e.getKey() + " " + e.getValue()));
    }

}
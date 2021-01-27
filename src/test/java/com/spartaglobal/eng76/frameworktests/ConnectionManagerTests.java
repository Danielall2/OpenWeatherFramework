package com.spartaglobal.eng76.frameworktests;

import com.spartaglobal.eng76.framework.connectionmanager.ConnectionManager;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConnectionManagerTests {
    private Properties properties;
    private ConnectionManager connectionManager;

    @BeforeAll
    void setup(){
         properties = new Properties();
         connectionManager = new ConnectionManager();
        try {
            properties.load(new FileReader("src/test/resources/apikey.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        connectionManager.connectToAPI("");






    }






}

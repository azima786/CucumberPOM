package com.qa.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private Properties prop;

    //Read multiple properties from config.properties
    public Properties init_prop() throws FileNotFoundException {
        prop = new Properties();
        try {
            FileInputStream ip = new FileInputStream("src/test/resources/Config/Config.properties");
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return prop;
    }
}

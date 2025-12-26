package org.example.utils;

import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    private PropertyReader() {}

    public static String readKey(String key) {

        Properties properties = new Properties();

        try (InputStream input =
                     PropertyReader.class
                             .getClassLoader()
                             .getResourceAsStream("data.properties")) {

            if (input == null) {
                throw new RuntimeException("data.properties file not found in src/main/resources");
            }

            properties.load(input);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return properties.getProperty(key);
    }
}

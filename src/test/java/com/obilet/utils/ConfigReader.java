package com.obilet.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;
    private static final String CONFIG_PATH = "src/test/resources/config.properties";

    static {
        try {
            FileInputStream input = new FileInputStream(CONFIG_PATH);
            properties = new Properties();
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("config.properties dosyası okunamadı: " + e.getMessage());
        }
    }

    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException(key + " config.properties dosyasında bulunamadı!");
        }
        return value;
    }
}

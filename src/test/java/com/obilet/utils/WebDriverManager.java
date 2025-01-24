package com.obilet.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.time.Duration;

public class WebDriverManager {
    private static WebDriver driver;
    private static final int TIMEOUT = 30;

    public static WebDriver getDriver() {
        if (driver == null) {
            io.github.bonigarcia.wdm.WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            driver = new FirefoxDriver(options);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TIMEOUT));
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}

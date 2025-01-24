package com.obilet.hooks;

import com.obilet.utils.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
    
    @Before
    public void setUp(Scenario scenario) {
        System.out.println("Starting scenario: " + scenario.getName());
        DriverManager.getDevice();
    }

    @After
    public void tearDown(Scenario scenario) {
        System.out.println("Finishing scenario: " + scenario.getName());
        if (scenario.isFailed()) {
            // Take screenshot or log additional information if needed
            System.out.println("Scenario failed: " + scenario.getName());
        }
        DriverManager.quitDriver();
    }
}

package com.obilet.hooks;

import com.obilet.utils.WebDriverManager;
import com.obilet.utils.VideoRecorder;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.ByteArrayInputStream;

public class Hooks {
    private VideoRecorder videoRecorder = VideoRecorder.getInstance();

    @Before
    public void setUp(Scenario scenario) throws Exception {
        System.out.println("Starting scenario: " + scenario.getName());
        WebDriverManager.getDriver();
        videoRecorder.startRecording(scenario.getName());
    }

    @After
    public void tearDown(Scenario scenario) throws Exception {
        System.out.println("Finishing scenario: " + scenario.getName());
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) WebDriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Failed Screenshot", new ByteArrayInputStream(screenshot));
            System.out.println("Scenario failed: " + scenario.getName());
        }
        videoRecorder.stopRecording();
        WebDriverManager.quitDriver();
    }
}

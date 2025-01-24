package com.obilet.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import androidx.test.ext.junit.runners.AndroidJUnit4;

@RunWith(AndroidJUnit4.class)
@CucumberOptions(
        features = "src/androidTest/assets/features",
        glue = {"com.obilet.stepdefinitions", "com.obilet.hooks"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber-pretty.html",
                "json:target/cucumber-reports/CucumberTestReport.json",
                "junit:target/cucumber-reports/CucumberTestReport.xml"
        },
        monochrome = true,
        dryRun = false,
        tags = "@obilet"
)
public class TestRunner {
}

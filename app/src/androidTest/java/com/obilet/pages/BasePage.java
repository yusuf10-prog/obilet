package com.obilet.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {
    protected AppiumDriver driver;
    protected WebDriverWait wait;
    private static final int WAIT_TIMEOUT = 15;

    public BasePage(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT));
    }

    protected void waitAndClick(WebElement element, String elementName) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
            System.out.println(elementName + " elementine tıklandı");
        } catch (Exception e) {
            System.err.println(elementName + " elementine tıklanamadı: " + e.getMessage());
            throw e;
        }
    }

    protected boolean isElementDisplayed(WebElement element, String elementName) {
        try {
            boolean isDisplayed = wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
            if (isDisplayed) {
                System.out.println(elementName + " elementi görüntülendi");
            } else {
                System.out.println(elementName + " elementi görüntülenemedi");
            }
            return isDisplayed;
        } catch (Exception e) {
            System.err.println(elementName + " elementi kontrol edilirken hata oluştu: " + e.getMessage());
            return false;
        }
    }

    protected void waitForElementVisibility(WebElement element, String elementName) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            System.out.println(elementName + " elementinin görünür olması beklendi");
        } catch (Exception e) {
            System.err.println(elementName + " elementi görünür olmadı: " + e.getMessage());
            throw e;
        }
    }

    protected String getElementText(WebElement element, String elementName) {
        try {
            String text = wait.until(ExpectedConditions.visibilityOf(element)).getText();
            System.out.println(elementName + " elementinin metni alındı: " + text);
            return text;
        } catch (Exception e) {
            System.err.println(elementName + " elementinin metni alınamadı: " + e.getMessage());
            throw e;
        }
    }
}

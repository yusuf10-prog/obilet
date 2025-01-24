package com.obilet.pages;

import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;
import com.obilet.utils.DriverManager;

public class HomePage {
    private final UiDevice device;

    public HomePage() {
        this.device = DriverManager.getDevice();
    }

    public void clickBusOption() {
        findAndClickElement("Otobüs");
    }

    public void clickFlightOption() {
        findAndClickElement("Uçak");
    }

    public void clickHotelOption() {
        findAndClickElement("Otel");
    }

    public void clickCarOption() {
        findAndClickElement("Araç");
    }

    private void findAndClickElement(String text) {
        try {
            UiObject element = device.findObject(new UiSelector().text(text));
            if (element.exists()) {
                element.click();
            }
        } catch (UiObjectNotFoundException e) {
            throw new RuntimeException("Element not found: " + text, e);
        }
    }

    public boolean isHomePageDisplayed() {
        try {
            UiObject busElement = device.findObject(new UiSelector().text("Otobüs"));
            UiObject flightElement = device.findObject(new UiSelector().text("Uçak"));
            UiObject hotelElement = device.findObject(new UiSelector().text("Otel"));
            UiObject carElement = device.findObject(new UiSelector().text("Araç"));

            return busElement.exists() && 
                   flightElement.exists() && 
                   hotelElement.exists() && 
                   carElement.exists();
        } catch (Exception e) {
            return false;
        }
    }
}

package com.obilet.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage extends BasePage {

    @FindBy(linkText = "Otobüs")
    private WebElement busOption;

    @FindBy(linkText = "Uçak")
    private WebElement flightOption;

    @FindBy(linkText = "Feribot")
    private WebElement ferryOption;

    @FindBy(linkText = "Otel")
    private WebElement hotelOption;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);

    }

    private void waitForPageLoad() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

    public boolean isHomePageLoaded() {
        waitForPageLoad();
        return isElementDisplayed(busOption, "Bus option button") &&
               isElementDisplayed(flightOption, "Flight option button") &&
               isElementDisplayed(ferryOption, "Ferry option button") &&
               isElementDisplayed(hotelOption, "Hotel option button");

    }

    public void clickBusOption() {
        waitAndClick(busOption, "Bus option button");
    }

    public void clickFlightOption() {
        waitAndClick(flightOption, "Flight option button");
    }

    public void clickFerryOption() {
        waitAndClick(ferryOption, "Ferry option button");
    }

    public void clickHotelOption() {
        waitAndClick(hotelOption, "Hotel option button");
    }

    public boolean verifyAllOptionsAreWorking() {
        clickBusOption();
        clickFlightOption();
        clickFerryOption();
        clickHotelOption();
        return true;
    }
}

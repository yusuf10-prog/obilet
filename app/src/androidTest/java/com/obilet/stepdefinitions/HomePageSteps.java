package com.obilet.stepdefinitions;

import com.obilet.pages.HomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class HomePageSteps {
    private final HomePage homePage;

    public HomePageSteps() {
        homePage = new HomePage();
    }

    @Given("the Obilet application is open")
    public void theObiletApplicationIsOpen() {
        // Uygulama DriverManager tarafından başlatılıyor
    }

    @Given("the home page is loaded")
    public void theHomePageIsLoaded() {
        Assert.assertTrue("Ana sayfa yüklenemedi", homePage.isHomePageLoaded());
    }

    @When("I click on the Bus option")
    public void iClickOnTheBusOption() {
        homePage.clickBusOption();
    }

    @When("I click on the Flight option")
    public void iClickOnTheFlightOption() {
        homePage.clickFlightOption();
    }

    @When("I click on the Hotel option")
    public void iClickOnTheHotelOption() {
        homePage.clickHotelOption();
    }

    @When("I click on the Car option")
    public void iClickOnTheCarOption() {
        homePage.clickCarOption();
    }

    @When("I click on the Ferry option")
    public void iClickOnTheFerryOption() {
        homePage.clickFerryOption();
    }

    @Then("verify all options are working properly")
    public void verifyAllOptionsAreWorkingProperly() {
        Assert.assertTrue("Tüm seçenekler düzgün çalışmıyor", homePage.verifyAllOptionsAreWorking());
    }
}

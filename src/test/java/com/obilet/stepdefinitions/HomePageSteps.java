package com.obilet.stepdefinitions;

import com.obilet.pages.HomePage;
import com.obilet.utils.WebDriverManager;
import io.cucumber.java.tr.*;
import org.junit.Assert;

public class HomePageSteps {
    private final HomePage homePage;

    public HomePageSteps() {
        homePage = new HomePage(WebDriverManager.getDriver());
    }

    @Diyelimki("kullanıcı obilet.com anasayfasına gider")
    public void userNavigatesToHomePage() {
        WebDriverManager.getDriver().get("https://www.obilet.com");
        Assert.assertTrue("Ana sayfa yüklenemedi", homePage.isHomePageLoaded());
    }

    @Eğerki("kullanıcı otobüs seçeneğine tıklar")
    public void userClicksBusOption() {
        homePage.clickBusOption();
    }

    @Ve("kullanıcı uçak seçeneğine tıklar")
    public void userClicksFlightOption() {
        homePage.clickFlightOption();
    }

    @Ve("kullanıcı feribot seçeneğine tıklar")
    public void userClicksFerryOption() {
        homePage.clickFerryOption();
    }

    @Ve("kullanıcı otel seçeneğine tıklar")
    public void userClicksHotelOption() {
        homePage.clickHotelOption();
    }

    @Ozaman("tüm seçeneklerin çalıştığı doğrulanır")
    public void verifyAllOptionsWork() {
        Assert.assertTrue("Tüm seçenekler çalışmıyor", homePage.verifyAllOptionsAreWorking());
    }
}

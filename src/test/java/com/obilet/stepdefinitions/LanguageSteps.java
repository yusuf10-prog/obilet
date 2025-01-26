package com.obilet.stepdefinitions;

import com.obilet.pages.BasePage;
import com.obilet.utils.WebDriverManager;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import org.apache.commons.io.FileUtils;
import java.util.List;
import java.io.IOException;
import static org.junit.Assert.assertTrue;

public class LanguageSteps extends BasePage {

    public LanguageSteps() {
        super(WebDriverManager.getDriver());
    }

    @When("kullanıcı dil seçim butonuna tıklar")
    public void userClicksLanguageButton() throws IOException {
        // Sayfanın tam olarak yüklenmesini bekle
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        System.out.println("Page loaded completely");
        
        try {
            // Screenshot al
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File("target/screenshots/homepage.png"));
            System.out.println("Screenshot saved as homepage.png");
            
            // Türk bayrağı butonunu bul
            List<WebElement> languageButtons = driver.findElements(
                By.cssSelector("[class*='language-select'], [class*='lang-select'], img[alt*='Türk'], img[src*='turkish'], [class*='turkish-flag']"));
            
            System.out.println("Found " + languageButtons.size() + " potential language buttons");
            
            if (!languageButtons.isEmpty()) {
                WebElement languageButton = languageButtons.get(0);
                wait.until(ExpectedConditions.elementToBeClickable(languageButton));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", languageButton);
                System.out.println("Clicked language button: " + languageButton.getAttribute("outerHTML"));
            } else {
                // Alternatif olarak data-language attribute'unu dene
                List<WebElement> altButtons = driver.findElements(
                    By.cssSelector("[data-language='tr'], [data-lang='tr'], button:has(img[alt*='Türk'])"));
                
                if (!altButtons.isEmpty()) {
                    WebElement altButton = altButtons.get(0);
                    wait.until(ExpectedConditions.elementToBeClickable(altButton));
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", altButton);
                    System.out.println("Clicked alternative language button: " + altButton.getAttribute("outerHTML"));
                } else {
                    throw new RuntimeException("Could not find Turkish flag button with any selector");
                }
            }
        } catch (Exception e) {
            System.out.println("Error while clicking language button: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @When("kullanıcı İngilizce seçeneğini seçer")
    public void userSelectsEnglish() {
        try {
            // Dil seçeneklerinin görünür olmasını bekle
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class*='language-select'], [class*='lang-select']")));
            System.out.println("Language select dropdown is visible");
            
            // İngilizce seçeneğini bul
            List<WebElement> englishOptions = driver.findElements(
                By.cssSelector("[data-language='en'], [class*='english'], [class*='lang-en']"));
            System.out.println("Found " + englishOptions.size() + " potential English options");
            
            if (!englishOptions.isEmpty()) {
                WebElement englishOption = englishOptions.get(0);
                wait.until(ExpectedConditions.elementToBeClickable(englishOption));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", englishOption);
                System.out.println("Clicked English option: " + englishOption.getAttribute("outerHTML"));
            } else {
                throw new RuntimeException("Could not find English language option");
            }
        } catch (Exception e) {
            System.out.println("Error while selecting English: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @Then("dilin İngilizce olarak değiştiği doğrulanır")
    public void verifyLanguageIsEnglish() {
        try {
            // Dil butonunun güncellenmiş halini bekle
            List<WebElement> languageButtons = driver.findElements(
                By.cssSelector("[class*='language'], [class*='lang'], [aria-label*='language']"));
            
            if (!languageButtons.isEmpty()) {
                WebElement languageButton = languageButtons.get(0);
                String buttonText = languageButton.getText().toUpperCase();
                System.out.println("Language button text: " + buttonText);
                assertTrue("Dil İngilizce olarak değişmedi. Buton metni: " + buttonText, 
                          buttonText.contains("EN") || buttonText.contains("ENG") || buttonText.contains("ENGLISH"));
            } else {
                throw new RuntimeException("Could not find language button for verification");
            }
        } catch (Exception e) {
            System.out.println("Error while verifying English language: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
}

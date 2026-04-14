package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigReader;
import java.time.Duration;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    protected final String URL = ConfigReader.get("base.url");
    protected final String USERNAME = ConfigReader.get("username");
    protected final String PASSWORD = ConfigReader.get("password");

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    protected void enterText(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(text);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void waitTillPageLoaded(String expectedUrl) {
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
    }
}
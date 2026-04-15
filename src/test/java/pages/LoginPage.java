package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class LoginPage extends BasePage {
    private static final Logger log = LogManager.getLogger(LoginPage.class);

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(name = "login")
    private WebElement loginField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitBtn;



    public void openPage() {
        log.info("User opens the login page: {}", URL);
        driver.get(URL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    public void enterUsername() {
        log.info("User enters username: {}", USERNAME);
        enterText(loginField,USERNAME);
    }

    public void enterPassword() {
        log.info("User enters password: {}", PASSWORD);
        enterText(passwordField,PASSWORD);
    }
    public void clickLoginBtn() {
        log.info("User clicks the login button");
        wait.until(ExpectedConditions.elementToBeClickable(submitBtn))
                .click();
    }
}
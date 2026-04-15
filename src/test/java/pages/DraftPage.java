package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DraftPage extends BasePage {
    private static final Logger log = LogManager.getLogger(DraftPage.class);

    public DraftPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//h2[contains(text(), 'Тут поки що порожньо')]")
    private WebElement confirmDraftText;

    @FindBy(id = "compose-subject")
    private WebElement topicField;

    @FindBy(xpath = "//p[contains(@class,'_0KWfiC5G')]//span")
    private WebElement addresseeTag;

    @FindBy(className = "ftTyQ6mI")
    private WebElement emailTopic;

    @FindBy(xpath = "(//button[text()='Надіслати'])[1]")
    private WebElement sendBtn;

    public boolean mailIsDisplayed() {
        log.info("User sees the email in the list");
        return wait.until(ExpectedConditions.visibilityOf(emailTopic))
                .isDisplayed();
    }

    public void openTheMail() {
        log.info("User opens the email from drafts");
        emailTopic.click();
    }

    public String getAddresseeText() {
        String addressee = wait.until(ExpectedConditions.visibilityOf(addresseeTag))
                .getText();
        log.info("User sees addressee: {}", addressee);
        return addressee;

    }

    public String getEmailTopicText() {
        String topic = wait.until(ExpectedConditions.visibilityOf(topicField))
                .getAttribute("value");
        log.info("User sees subject: {}", topic);
        return topic;
    }

    public String getMailBodyInputText() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(
                By.cssSelector("iframe[id$='_ifr']")));
        String actual = driver.findElement(By.id("tinymce")).getText();
        driver.switchTo().defaultContent();
        log.info("User sees email body: {}", actual);
        return actual;
    }

    public void sendTheMail() {
        log.info("User clicks the Send button");
        sendBtn.click();
    }

    public boolean isDraftPageClear() {
        log.info("User sees drafts folder is empty");
        return wait.until(ExpectedConditions.visibilityOf(confirmDraftText))
                .isDisplayed();
    }
}
package tests;

import drivers.DriverFactory;
import pages.DraftPage;
import pages.InboxPage;
import pages.LoginPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class BaseTest {
    private static final Logger log = LogManager.getLogger(BaseTest.class);

    protected WebDriver driver;
    protected LoginPage loginPage;
    protected InboxPage inboxPage;
    protected DraftPage draftPage;

    @Parameters("browser")
    @BeforeClass
    public void setUp(@Optional("chrome") String browser) {
        log.info("=== Starting tests on browser: {} ===", browser);
        DriverFactory.initializeDriver(browser);
        driver = DriverFactory.getDriver();
        loginPage = new LoginPage(driver);
        inboxPage = new InboxPage(driver);
        draftPage = new DraftPage(driver);
    }

    @AfterClass
    public void closeBrowser() {
        log.info("=== Closing browser ===");
        DriverFactory.tearDown();
    }
}
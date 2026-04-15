package drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    public static void initializeDriver(String browser) {
        if (driver == null) {
            switch (browser.toLowerCase()) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "edge":
                     /*
                On my local WebDriver Manager for some reason can`t connect to repository with Edge driver,
                so I get "java.net.UnknownHostException: msedgedriver.azureedge.net" exception
                to avoid this exception I downloaded and set WebDriver locally
                If you are facing same issue please download and set up WebDriver locally as well,
                and adjust WebDriver path in setProperty method below
                 */
                    System.setProperty("webdriver.edge.driver", "D:\\webdriver\\msedgedriver.exe");
//                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                default:
                    throw new IllegalArgumentException("This browser don`t supported: " + browser);
            }
            driver.manage().window().maximize();
        }
    }

    public static void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
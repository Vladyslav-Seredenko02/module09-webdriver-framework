package Listeners;

import Drivers.DriverFactory;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;

public class AllureListener implements ITestListener {
    private static final Logger log = LogManager.getLogger(AllureListener.class);

    @Override
    public void onTestFailure(ITestResult result) {
        log.error("Test failed: {}", result.getName());

        byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver())
                .getScreenshotAs(OutputType.BYTES);

        Allure.addAttachment("Screemshot", new ByteArrayInputStream(screenshot));
        log.info("Screenshot attached to Allure report");
    }
}
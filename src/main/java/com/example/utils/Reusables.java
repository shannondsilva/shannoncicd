package com.example.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.hc.core5.util.Timeout;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Reusables {

    static final Logger LogCapture = LogManager.getLogger(Reusables.class);


    public String openBrowser(String data, String value) {
        try {
            LogCapture.info("openBrowser method called");
            WebDriverManager.chromedriver().setup();
            Constants.driver = new ChromeDriver();
            Constants.driver.manage().window().maximize();
            LogCapture.info("openBrowser method executed");
            return Constants.PassedBlock;
        } catch (Exception e) {
            LogCapture.error("openBrowser method failed");
            return Constants.FailedBlock;
        }
    }

    public String click(String data, String value) {
        try {
            LogCapture.info("click method called");
            Constants.driver.close();
            LogCapture.info("click method executed");
            return Constants.PassedBlock;
        } catch (Exception e) {
            LogCapture.error("click method failed");
            return Constants.FailedBlock;
        }
    }

    public String visibleWaitCondition(String data, String value) {
        try {
            LogCapture.info("visibleWaitCondition method called");
            WebDriverWait wait = new WebDriverWait(Constants.driver, Duration.ofSeconds(Long.parseLong(value)));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(data)));
            LogCapture.info("visibleWaitCondition method executed");
            return Constants.PassedBlock;
        } catch (Exception e) {
            LogCapture.error("visibleWaitCondition method failed");
            return Constants.FailedBlock;
        }
    }

    public String InvisibleWaitCondition(String data, String value) {
        try {
            LogCapture.info("InvisibleWaitCondition method called");
            WebDriverWait wait = new WebDriverWait(Constants.driver, Duration.ofSeconds(Long.parseLong(value)));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(data)));
            LogCapture.info("visibleWaitCondition method executed");
            return Constants.PassedBlock;
        } catch (Exception e) {
            LogCapture.error("InvisibleWaitCondition method failed");
            return Constants.FailedBlock;
        }
    }


    public String navigateToURL(String data, String value) {
        try {
            LogCapture.info("navigateToURL method called");
            Constants.driver.navigate().to(Constants.config.getProperty(data));
            LogCapture.info("navigateToURL method executed");
            return Constants.PassedBlock;
        } catch (Exception e) {
            LogCapture.error("navigateToURL method failed");
            return Constants.FailedBlock;
        }
    }

}

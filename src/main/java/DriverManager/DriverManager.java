package DriverManager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.time.Duration;
import java.util.*;

public class DriverManager {
    private static WebDriver driver;


    public static WebDriver getDriver() {
        if (driver == null) {
            EdgeOptions options = new EdgeOptions();
            options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
            options.setExperimentalOption("useAutomationExtension", false);
            options.addArguments("--disable-blink-features=AutomationControlled");
// Ensure stable execution in Azure Pipeline (headless mode)
            options.addArguments("--headless=new"); // New headless mode (improves stability)
            options.addArguments("--disable-gpu"); // Disable GPU acceleration
            options.addArguments("--no-sandbox"); // Bypass OS security restrictions
            options.addArguments("--disable-dev-shm-usage"); // Prevent shared memory issues
            options.addArguments("--remote-debugging-port=9222"); // Fix "DevToolsActivePort" issue

            driver = new EdgeDriver(options);


            WebDriverManager.edgedriver().setup();


           // driver = new EdgeDriver();


            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
